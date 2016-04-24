package test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import main.CommandHandler;

/**
 * Test class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Test {

  /**
   * 1. Töltényt vált
   * 
   * A kiválasztott karakter töltényének színe az ellenkezőjére vált.
   * Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a kilőhető
   * töltény színe megváltoztatható-e. A karaktert mozgatjuk keletre, hogy
   * biztosan a fal felé nézzen. Lövünk egy töltényt, ennek kék csillagkaput
   * kell létrehozni a speciális falon, majd töltényt váltunk, újra lövünk,
   * ebben esetben sárga csillagkapunak kell létrejönnie. Várható hibák második
   * lövésnél nem változik a csillagkapu színe
   * 
   * Elvárt kimenet
   * SPy | FWO | SPb |
   */

  public static boolean changeBulletColor() throws IOException {
    File mapFile = new File("level_test1.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("SP O SP");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test1.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("changebullet oneill");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[SPy, FWO, SPb]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 2. Lő
   * 
   * A karakter lő egyet arra, amerre néz.
   * Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter létrehozta-e a lövedéket a megfelelő
   * színnel. Sikeres futás esetén megjelenik egy csillagkapu a karakter
   * melletti speciális falon.
   * Várható hibák: Nem jön létre lövedék, tehát a
   * speciális falon se jelenik meg csillagkapu
   * 
   * FWO | SPb |
   */

  public static boolean shootBullet() throws IOException {
    File mapFile = new File("level_test2.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O SP");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test2.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FWO, SPb]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 3. Lövedék falnak ütközik
   * 
   * A lövedék ütközését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a lövedék megsemmisül-e falba ütközéskor. Ezt úgy
   * ellenőrizzük, hogy a fal mögé egy speciális falat helyezünk, ha a speciális
   * falon létrejön egy csillagkapu, az hibás működés jelentene, mert az
   * általános fal nem állította meg a lövedéket.
   * 
   * FWO | FNW | SP |
   */

  public static boolean bulletMeetsWall() throws IOException {
    File mapFile = new File("level_test3.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FNW SP");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test3.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FWO, FNW, SP]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 4. Lövedék speciális falnak ütközik
   * 
   * A lövedék ütközését teszteli. Ellenőrzött funkcionalitás, várható
   * hibahelyek Teszteli, hogy a lövedék megsemmisül-e a speciális falba
   * ütközéskor és létrehoz-e csillagkapu nélküli speciális falon csillagkaput a
   * lövedék színe alapján. A pályán két speciális fal van egymás mögött, helyes
   * működés esetén az O’Neill-hoz legközelebb levő speciális falon jelenik meg
   * csillagkapu. Várható hibalehetőség: Az speciális fal mögötti speciális
   * falon jelenik meg csillagkapu Nem jön létre csillagkapu
   * 
   * FWO | SPb | SP |
   */

  public static boolean bulletMeetsSpecWall() throws IOException {
    File mapFile = new File("level_test4.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O SP SP");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test4.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FWO, SPb, SP]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 5. Lövedék replikátornak ütközik
   * 
   * A lövedék ütközését teszteli. Ellenőrzött funkcionalitás, várható
   * hibahelyek Teszteli, hogy a lövedék és a replikátor megsemmisül-e, ha a
   * lövedék replikátornak ütközik. O’Neill a replikátor felé lő, a replikátor
   * mögött van egy speciális fal. Helyes működés esetén, a replikátor
   * megsemmisül, és a speciális falon _nem_ jön létre csillagkapu. Várható
   * hibák: Replikátor nem semmisül meg Replikátor megsemmisül, de a speciális
   * falon létrejön egy csillagkapu
   * 
   * FW | FWO | FW | SP |
   */

  public static boolean bulletMeetsReplicator() throws IOException {
    File mapFile = new File("level_test5.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FW R SP");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test5.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FWO, FW, SP]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 6. Lövedék ajtóba ütközik
   * 
   * A lövedék ütközését teszteli. Ellenőrzött funkcionalitás, várható
   * hibahelyek Teszteli, hogy a lövedék megsemmisül-e zárt ajtóval való ütközés
   * esetén. A pályán a zárt ajtó mögött van egy speciális fal, helyes működés
   * esetén a speciális falon nem jön létre csillagkapu. Várható hibák: A
   * lövedék átmegy a zárt ajtón és a speciális falon megjelenik egy csillagkapu
   * 
   * FW | FWO | D1NW| SP | SC1 |
   */

  public static boolean bulletMeetsDoor() throws IOException {
    File mapFile = new File("level_test6.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FW D1 SP SC1");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test6.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FWO, D1NW, SP, SC1]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 7. Lövedék nem ütközik
   * 
   * A lövedék haladását teszteli átjárható mezők felett. Ellenőrzött
   * funkcionalitás, várható hibahelyek Teszteli, hogy a lövedék át tud-e
   * haladni az átjárható mezők felett. A tesztelt mezők: Floor, Gap, Door
   * (nyitva). Helyes működésnél az előbb felsorolt mezők melletti speciális
   * falon létrejön egy csillagkapu. Várható hibák: nem jön létre csillagkapu,
   * tehát a lövedék elakad valamelyik mezőn
   * 
   * FW | SC1 | G | D1NW| SPb |
   * FW | FW | FWO | G | SPy |
   */

  public static boolean bulletFly() throws IOException {
    File mapFile = new File("level_test7.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O SC1 G D1 SP\nFW FW FW G SP");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test7.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("move oneill s");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("changebullet oneill");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, SC1, G, D1NW, SPb], [FW, FW, FWO, G, SPy]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 8. Tárgyat sikeresen felvesz
   * 
   * A karakter felvesz egy tárgyat egy olyan mezőről, melyen van. Ellenőrzött
   * funkcionalitás, várható hibahelyek Teszteli, hogy a tárgyat sikeresen
   * felvette-e a karakter, tehát eltűnt-e a mezőről. Várható hibák: a mezőn
   * megmarad a doboz a mezőn megmarad a ZPM
   * 
   * FW | FW | FWO |
   */

  public static boolean pickupItemSuccessfully() throws IOException {
    File mapFile = new File("level_test8.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FB FZ");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test8.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FW, FWO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 9. Tárgyat felvesz tiltott mezőről
   * 
   * A karakter megkísérel felvenni egy tárgyat egy olyan mezőről, melyre nem
   * lehet tárgyat helyezni.
   * Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter tud-e felvenni tárgyat olyan mezőről, amin nem
   * lehet tárgy. A speciális fal, amin csaillagkapu van az egy olyan mező, amire 
   * leehet lépni, de tárgy nem helyezhető rá. Az ezredes kilő a speciális falra
   * egy csillagkapu, rálő, rálép, megkísérel felvenni valamit, majd a szomszédos
   * mezőre lerakja. Helyes működés esetén nem rak le semmit.
   * 
   * FW | SPb | FWO |
   */

  public static boolean pickupItemFromForbiddenArea() throws IOException {
    File mapFile = new File("level_test9.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O SP FW");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test9.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, SPb, FWO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 10. Tárgyat felvesz tárgyat nem tartalmazó mezőről
   * 
   * A karakter megkísérel felvenni egy tárgyat egy olyan mezőről, melyen
   * nincsen tárgy (de lehetne). Ellenőrzött funkcionalitás, várható hibahelyek
   * A teszt ellenőrzi, hogy a karakter tud-e felvenni tárgyat olyan mezőről
   * ahol nincs tárgy, Hibás, ha a karaterhez tárgy kerül azt nem tartalmazó
   * mezőről.
   * 
   * FWO | FW | FW |
   */

  public static boolean pickupItemNoItem() throws IOException {
    File mapFile = new File("level_test10.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FW FW");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test10.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FWO, FW, FW]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 11. Dobozt felvesz, de már van
   * 
   * A karakter megkísérel felvenni egy dobozt, miközben már van nála.
   * Ellenőrzött funkcionalitás, várható hibahelyek A teszt ellenőrzi, hogy a
   * karakter fel tud-e venni dobozt, ha már van egy nála. Hibás ha a doboz
   * eltűnik a mezőről ill. a karakterhez kerül.
   * 
   *   FW |  FW | FWBO |
   */

  public static boolean pickupSecondBox() throws IOException {
    File mapFile = new File("level_test11.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FB FB");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test11.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("printmap");

    String expectedOutput = "[[FW, FW, FWBO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 12. Dobozt felvesz mérlegről
   * 
   * A karakter felvesz egy dobozt egy mérlegről. Ellenőrzött funkcionalitás,
   * várható hibahelyek A teszt ellenőrzi, hogy a doboz felvétele után a
   * mérlegről valóban eltűnt-e a doboz. Hibás ha a doboz a helyén marad, nem
   * kerül a karakterhez, vagy megsemmisül.
   * 
   * FW | FWBO | SC1 | D1NW |
   */

  public static boolean pickupBoxFromScale() throws IOException {
    File mapFile = new File("level_test12.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FB SC1 D1");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test12.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FWBO, SC1, D1NW]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 13. Dobozt sikeresen letesz
   * 
   * A karakter leteszi a néla lévő dobozt egy olyan mezőre, melyen nincs tárgy
   * (de lehetne). Ellenőrzött funkcionalitás, várható hibahelyek A teszt
   * ellenőrzi, hogy a doboz valóban a megfelelő cellára került. Hibás, ha a
   * doboz a karakternél marad vagy eltűnik, azaz nem kerül a mezőre.
   * 
   * FW |  FW | FWBO |
   */

  public static boolean dropBoxSuccesfully() throws IOException {
    File mapFile = new File("level_test13.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FB FW");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test13.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FW, FWBO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 14. Dobozt letesz tiltott mezőre
   * 
   * A karakter megkísérel letenni egy dobozt egy olyan mezőre, melyre nem lehet
   * tárgyat helyezni. Ellenőrzött funkcionalitás, várható hibahelyek A teszt
   * ellenőrzi, hogy a karakter le tud-e tenni olyan mezőre dobozt, amire nem
   * lehet. Hibás ha a mezőre rákerül a doboz.
   * 
   * FW |  FW | SPbO |
   */

  public static boolean dropBoxForbiddenArea() throws IOException {
    File mapFile = new File("level_test14.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FB SP");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test14.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FW, SPbO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 15. Dobozt letesz tárgyat tartalmazó mezőre
   * 
   * A karakter megkísérel letenni egy dobozt egy olyan mezőre, melyen már van
   * tárgy. Ellenőrzött funkcionalitás, várható hibahelyek A teszt ellenőrzi,
   * hogy lehet-e lerakni dobozt olyan mezőre, melyen van már tárgy. A játékos
   * felvesz egy dobozt, majd megpróbálja lerakni az előbbi mezőkre. Hibás ha
   * sikerül neki, azaz a doboz nem kerül vissza az eredeti helyére.
   * 
   * FWO | FW | FWZ | FWB |
   */

  public static boolean dropBoxGotItem() throws IOException {
    File mapFile = new File("level_test15.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FB FZ FB");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test15.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FWO, FW, FWZ, FWB]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 16. Dobozt letesz, de még nincs
   * 
   * A karakter megkísérel letenni egy dobozt, miközben még nincs nála.
   * Ellenőrzött funkcionalitás, várható hibahelyek A teszt ellenőrzi, hogy a
   * karakter tud-e letenni dobozt, ha nincs nála. Hibás ha a doboz rákerül a
   * mezőre.
   * 
   * FWO | FW |
   */

  public static boolean dropBoxNoBox() throws IOException {
    File mapFile = new File("level_test16.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FW");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test16.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FWO, FW]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 17. Dobozt letesz mérlegre
   * 
   * A karakter dobozt helyez egy mérlegre. Ellenőrzött funkcionalitás, várható
   * hibahelyek Teszteli, hogy a lehelyezett mérleg tömege ránehezedik-e a
   * mérlegre. Várható hibák: doboz nem kerül rá a mérlegre, megmarad a
   * karakternél doboz rákerül a mérlegre, de a játékosnál is megmarad doboz nem
   * kerül a mérlegre, és a játékostól is eltűnik
   * 
   * FW | FW | SC1BO | D1W |
   */

  public static boolean dropBoxOnScale() throws IOException {
    File mapFile = new File("level_test17.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FB SC1 D1");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test17.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FW, SC1BO, D1W]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 18. Karakter átjárható mezőre lép
   * 
   * A karakter lépését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter át tud-e lépni átjárható mezőre. Várható hibák:
   * Karakter nem változtat pozicíót.
   * 
   * FW | FWO |
   */

  public static boolean stepOnWalkable() throws IOException {
    File mapFile = new File("level_test18.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FW");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test18.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FWO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 19. Karakter falnak ütközik
   * 
   * A karakter lépését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter miként viselkedik, ha Falként kezelt mezőre akar
   * lépni. Várható hibák: A karakter rá tud lépni a mezőre, holott nem szabadna
   * neki. A karakter nem arra a mezőre kerül vissza, amiről megpróbált
   * “rálépni” a falra.
   * 
   * FW | FWO | FNW |
   */

  public static boolean playerHitWall() throws IOException {
    File mapFile = new File("level_test19.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FW FNW");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test19.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FWO, FNW]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 20. Karakter szakadékba lép
   * 
   * A karakter lépését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter miként viselkedik, ha Szakadékként kezelt mezőre
   * akar lépni. Várható hibák: A karakter rá tud lépni a mezőre és ott is
   * marad, mint játszható figurai. A karakter nem hal meg azután, hogy rálépett
   * a mezőre. A karakter “halála után” is a mezőn marad.
   * 
   * FW | FW | G |
   */

  public static boolean stepOnGap() throws IOException {
    File mapFile = new File("level_test20.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FW G");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test20.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");

    String expectedOutput = "[[FW, FW, G]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 21. Replikátor szakadékba lép
   * 
   * A replikátor lépését teszteli. Ellenőrzött funkcionalitás, várható
   * hibahelyek Teszteli, hogy a replikátor szakadékba lépésekor betölti-e a
   * szakadékot és a replikátor megsemmisül-e. Várható hibák: Replikátor nem hal
   * meg, miután a nyílt szakadékba lépet. Szakadék nem tömődik be, miután a
   * replikátor rálépet.
   * 
   * FW | GW |
   * Replikator meghalt
   * Szakadék walkable == true 
   */

  public static boolean replicatorStepOnGap() throws IOException {
    File mapFile = new File("level_test21.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("R G");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test21.txt");
    CommandHandler.executeCommand("move replicator e");
    CommandHandler.executeCommand("printmap");
    //TODO ha akarunk extra ellenőrzst (replikátor halál, szakadék walkable, az jöhet ide)
    String expectedOutput = "[[FW, GW]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 22. Karakter csillagkapuba lép
   * 
   * A karakter lépését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter át tudd-e lépni a csillagkapura. Várható hibák:
   * Nem tudd rálépni a Specwall-ra miután csillagkapu van rajta. Nincs ott a
   * csillagkapu, miután a töltény eltalálta a Specwall-t.
   * 
   * | FW | FW | SPbO |
   */

  public static boolean stepOnStarGate() throws IOException {
    File mapFile = new File("level_test22.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FW SP");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test22.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FW, SPbO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 23. Karakter féregjáratba lép
   * 
   * A karakter lépését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter csillagkapuba lépésekor át kerül-e a csillagkapu
   * párjára. Várható hibák: Karakter nem kerül át a másik csillagkapura, miután
   * rálépett a párjára.
   * 
   * SPy | FW | FW | SPbO |
   */

  public static boolean stepInWormHole() throws IOException {
    File mapFile = new File("level_test23.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("SP O FW SP");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test23.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("changebullet oneill");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("printmap");
    
    //TODO féregjárat nem helyez át, lőhető két ugyanolyan színű stargate két különböző specwallra
    String expectedOutput = "[[SPy, FW, FW, SPbO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 24. Karakter ajtóba ütközik
   * 
   * A karakter lépését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter a helyén marad-e, ha zárt ajtónak ütközik.
   * Várható hibák: Karaktár átlép az ajtóra, úgy hogy az zárva van.
   * 
   * SC1 | FWO | D1NW|
   */

  public static boolean stepOnDoorNonWalkable() throws IOException {
    File mapFile = new File("level_test24.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("SC1 O D1");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test24.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[SC1, FWO, D1NW]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 25. Karakter nyílt ajtóra lép
   * 
   * A karakter lépését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter át tud-e lépni nyitott ajtó mezejére. Várható
   * hibák: Ajtó nincs nyitva, miután a mérleg aktíválta. Karakter nem tudd
   * rálépni a nyilt ajtóra.
   * 
   * FW | FW | FW | SC1BB | D1WO |
   */

  public static boolean stepOnDoorWalkable() throws IOException {
    File mapFile = new File("level_test25.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FB FB  SC1 D1");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test25.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("drop oneill");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FW, FW, SC1BB, D1WO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /** 
   * 26. Karakter mérlegre lép
   * 
   * A karakter lépését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter át tud-e lépni nyitott ajtó mezejére. Várható
   * hibák: Karakter nem tudd átlépni a mérlegre.
   * 
   * FW | SC1O | D1W |
   */

  public static boolean stepOnScale() throws IOException {
    File mapFile = new File("level_test26.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O SC1 D1");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test26.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, SC1O, D1W]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 27. Karakter mérlegről lelép
   * 
   * A karakter lépését teszteli. Ellenőrzött funkcionalitás, várható hibahelyek
   * Teszteli, hogy a karakter a tömegével leszáll-e a mérlegről. Várható hibák:
   * Karakter mérlegen marad, walkable-re lépés után.
   * 
   * FW | SC1 | FWO | D1NW |
   */

  public static boolean stepOffScale() throws IOException {
    File mapFile = new File("level_test27.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O SC1 FW D1");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test27.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, SC1, FWO, D1NW]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 28. Mérleg aktiválása
   * 
   * Mérleg aktívra váltását teszteli. Ellenőrzött funkcionalitás, várható
   * hibahelyek Teszteli, hogy a mérleg a hozzá megszabott súlyhatár alulról
   * átlépésekor aktívra vált-e. Várható hibák: Mérleg limitének átlépése után
   * nem aktiválódik.
   * 
   * FW | SC1O | D1W |
   */

  public static boolean activateScale() throws IOException {
    File mapFile = new File("level_test28.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O SC1 D1");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test28.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, SC1O, D1W]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 29. Mérleg deaktiválása
   * 
   * Mérleg inaktívra váltását teszteli. Ellenőrzött funkcionalitás, várható
   * hibahelyek Teszteli, hogy a méleg a hozzá megszabott súlyhatár felülről
   * átlépésekor inaktívra vált-e. Várható hibák: Mérleg aktív marad, miután a
   * rajta lévő súly a limitje alá esik.
   * 
   * SC1 | FWO | D1NW |
   */

  public static boolean deActivateScale() throws IOException {
    File mapFile = new File("level_test29.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("SC1 O D1");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test29.txt");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("printmap");

    String expectedOutput = "[[SC1, FWO, D1NW]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 30. Súgó
  * 
  * Súgó megjelenitése Ellenőrzött funkcionalitás, várható hibahelyek
  * Megjelenik-e parancs beadására a súgó. Bemenet help Elvárt kimenet loadmap
  * <level file txt> move <character name: oneill, jaffa, replicator>
  * <direction n, e, s, w> changebullet <character name: oneill, jaffa> shoot
  * <character name: oneill, jaffa> pickup <character name: oneill, jaffa> drop
  * <character name: oneill, jaffa> printmap help zpmcount <character name:
  * oneill, jaffa> setrandomzpmposition <number of steps from oneill in each
  * direction 0 0 0 0> 
  */
  
  public static void helpTest() throws IOException {
    CommandHandler.executeCommand("help");
    
    String expectedOutput = "loadmap <level file txt>\n"
        + "move <character name: oneill, jaffa, replicator> <direction n, e, s, w>\n"
        + "changebullet <character name: oneill, jaffa>\n" + "shoot <character name: oneill, jaffa>\n"
        + "pickup <character name: oneill, jaffa>\n" + "drop <character name: oneill, jaffa>\n" + "printmap\n"
        + "help\n" + "zpmcount <character name: oneill, jaffa>\n"
        + "setrandomzpmposition <number of steps from oneill in each direction 0 0 0 0>\n";
  
  //TODO hogyan kéne ezt elkapni
  }
  
  /**
   * 31. Pálya betöltés
   * Leírás Ellenőrzött funkcionalitás,
   * várható hibahelyek Teszteli, hogy az adott fájlból a várt pálya jött-e
   * létre.
   * 
   * FW | FNW | SC1 | D1NW | 
   * FWO | FWJ | FWR | FWB | 
   * FWZ |  SP |   G |   G | 
   */

  public static boolean loadMapTest() throws IOException {
    File mapFile = new File("level_test31.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("FW FNW SC1 D1\nO J R FB\nFZ SP G G");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test31.txt");
    System.out.println(CommandHandler.levelBuilder.getLevelAsString());
    System.out.println(CommandHandler.levelBuilder.getLevelAsString2());
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FNW, SC1, D1NW], [FWO, FWJ, FWR, FWB], [FWZ, SP, G, G]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);    
  }

  /**
   * 32. Harmadik ZPM megjelenése
   * 
   * Két ZPM felvétele után megjelenik-e egy új. Ellenőrzött funkcionalitás,
   * várható hibahelyek Teszteli, hogy a következő ZPM létrejön-e. A játékban ez
   * véletlenszerű helyre történik, de a tesztelés során a parancsban definiált
   * pozicíón fog megjelenni. Várható hibák: ZPM nem jelenik meg.
   * 
   * FW | FW | FWO | FW | FZ |
   */

  public static boolean thirdZpmAppear() throws IOException {
    File mapFile = new File("level_test32.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FZ FZ FW FW");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test32.txt");
    CommandHandler.executeCommand("setrandomzpmposition 0 2 0 0");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill ");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill ");
    CommandHandler.executeCommand("printmap");
    
    String expectedOutput = "[[FW, FW, FWO, FW, FWZ]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    return expectedOutput.equals(printMapOutput);
  }

  /**
   * 33. Játék befejezése
   * 
   * Játék befejezése, ha elfogytak a ZPM-ek elfogytak. Ellenőrzött
   * funkcionalitás, várható hibahelyek Teszteli, hogy a játék befejeződik-e,
   * hogyha minden ZPM-et felvettek a játékosok. Várható hibák: Játék
   * folytatódik, miután elfogytak a ZMP-ek.
   */

  public static void gameOver() throws IOException {
    File mapFile = new File("level_test33.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("O FZ");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test32.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    
    //Alább az elvárt kimenet megíró, aktuális kimenet lekérés megíró, return megírü sor
    //System.out.println("String expectedOutput = " + "\"" + CommandHandler.levelBuilder.getLevelAsString() + "\";\n" + "String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();\n" + "return expectedOutput.equals(printMapOutput);");

    //TODO Játék fejeződjön be?
  }

  /**
   * 
   * 34. ZPM számláló növekedés
   * 
   * Az egyik játékos felvesz egy ZPM-et. Növekszik-e a zpmcount? Ellenőrzött
   * funkcionalitás, várható hibahelyek Teszteli, hogy a zpmCount nő-e egyel.
   * Várható hibák: A zpmCount nem növekszik
   * 
   * 0
   * FWZ  |  FW  |  FWO  |
   * 1
   */

  public static boolean zpmCountIncrement() throws IOException {
    File mapFile = new File("level_test34.txt");
    if (!mapFile.exists()) {
      mapFile.createNewFile();
    }
    PrintWriter mapFileWriter = new PrintWriter(mapFile, "UTF-8");
    mapFileWriter.print("FZ O FZ");
    mapFileWriter.close();
    CommandHandler.executeCommand("loadmap level_test34.txt");
    CommandHandler.executeCommand("zpmcount oneill");
    int zpmCount1 = CommandHandler.levelBuilder.getOneill().getZpmCount();
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("pickup oneill");
    CommandHandler.executeCommand("printmap");
    CommandHandler.executeCommand("zpmcount oneill");
    int zpmCount2 = CommandHandler.levelBuilder.getOneill().getZpmCount();
    
    String expectedOutput = "[[FWZ, FW, FWO]]";
    String printMapOutput = CommandHandler.levelBuilder.getLevelAsString();
    
    return expectedOutput.equals(printMapOutput) && (zpmCount1 == 0) && (zpmCount2 == 1);
  }
}
