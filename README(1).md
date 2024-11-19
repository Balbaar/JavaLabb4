# Laboration 4
(Version 0.2)

Nu ska vi lära oss mer om listor, genom att skapa en fifo-kö.
Vi börjar med en mjukstart och gör en kö som delegerar ansvaret till en vanlig länkad lista.
Sedan ska vi ersätta den med egen struktur,
implementera en iterator för den och till slut lära oss att slå ihop listor, på ett smart sätt.

Labben kommer resultera i en klass och tester för den klassen.
Du kan själv välja vilket paket du vill lägga din klass i. Testerna ska ligga i samma paket, men kan ligga i en annan mapp.

## Att fundera på
- Skulle du lika gärna kunna använda ArrayList för att lagra elementen i FifoQueue
i uppgift 1?
- Jämför de två olika sätten att implementera FifoQueue i uppgift ett och två.
Fördelar/nackdelar?
- Istället för att implementera en egen kö-klass skulle man helt enkelt kunna använda
någon av kö-klasserna i `java.util` (`LinkedList` eller `ArrayDeque`). Ofta är det klokt
att återanvända en befintlig implementering på detta sätt. I vilka situationer kan det
vara olämpligt?
- Vid testning av dina klass `FifoQueue` får du grönt ljus. Kan du då vara säker på att
din klass är felfri?

## Relevanta delar i Moodle
4:1, 4:2, 6:1 & 6:2

## Del 1, Delegera till en befintlig struktur
### Syfte
Lära känna metoderna i en FIFO-kö utan att behöva
skapa en från grunden.

### Teknik
Skapa en Fifokö som delegerar ansvaret för elementen till en länkad lista från Javas standardbibliotek.
`java.util.LinkedList`.

### Filer att ändra i
- `FifoQueue.java` (Skapas)
- `FifoQueueTest.java` (Skapas)

### Instruktioner
1. Skapa klassen `FifoQueue<E>`. Den ska ärva `AbstractQueue<E>` från `java.util` och implementerar alltså `Queue<E>`, genom den.
2. Generera skal för de metoder som krävs:
   -  public int size() — låt den returnera 0
   -  public boolean offer(E e) — låt den returnera false
   -  public E poll() — låt den returnera null
   -  public E peek() — låt den returnera null
   -  public Iterator<E> iterator() - låt den returnera null
   
3.  Skriv följande tester, och få dem att passera, _ett_ _i_ _taget_.
    Använd strängar och låt E vara String.
   - size() för en tom kö är 0.
   - offer() returnerar sant.
   - offer() ökar storleken på kön.
   - peek() ändrar inte listans storlek.
   - peek() på en tom kö returnerar null.
   - peek() returnerar första elementet (första strängen).
   - poll() returnerar null för en tom kö.
   - poll() returnerar första objektet.
   - poll() tar bort första elementet.
   - iterator() returnerar en iterator som har en next().

## Del 2, skriv en egen kö 
### Syfte
Förstå hur en datastruktur ser ut inuti och känna er trygga att skriva en egen, 
om ni mot förmodan behöver.
Det kommer också (troligtvis) komma på tentan.

### Teknik
En enkellänkad lista, som använder en inre statisk Nod-klass för att hålla elementen.
Vi kommer göra ändringarna i den befintliga klassen och använda testerna
du redan skrivit för att först ha två parallella inre strukturer, 
och sedan helt byta ut vår inre länkade lista.

Listan ska vara enkellänkad, rundlänkad och om det bara finns en nod ska den ha sig själv som `next`.
`FifoQueue` får bara ha referens till en `Node`, och den ska heta `last`.

Behöver du veta mer om länkade listor finns det ett avsnitt i CLUES.md
Där beskrivs också inre klasser.

Koden ska skrivas testdrivet.
Om ett test passerar direkt, ha sönder koden lite, så du ser att testet funkar, fixa det sedan igen.

### Filer att ändra i
- `FifoQueue.java` 
- `FifoQueueTest.java` 

### Instruktioner
- Läs på om länkade listor på Moodle. Det finns också text i Clues.
- Skissa på hur en länkad lista fungerar på ett papper. 
    Rita ut de olika noderna, och vad som händer med referenserna när ett element sätts in eller tas ut.
- Ge `FifoQueue` ett attribut `last` som referera till den inre klassen `Node`.
    Sätt attributet till `null` i konstruktorn.
    Skapa klassen och se till att testerna kör. `Node` ska inte göra något än. 
- Låt `Node` ha två attribut, `element` av typen `E` och `next` av typen `Node`.
    Konstruktorn ska ta ett argument av typen `E`som tilldelas till `element` och sätta `next` till `null`.
- Lägg till rader först i metoden `offer` som skapar en ny nod. Den första noden att sättas in bli listans `last`.
  Eftersom listan är cirkulär så behöver varje ny nod efter det sättas in mellan den nod som innan var sist och första noden,
som det sista elementet alltid ska peka på. Är det bara en nod ska den peka på sig själv. Rita om det är svårt.
- Lägg till rader först i metoden `poll` som ändrar i vår nya struktur.
- Ända i `size` så att den inte använder den gamla länkade listan.
- Ändra `peek` så att den inte använder den gamla länkade listan, utan bara de nya noderna.
- Skriv ett test som kollar att listan går att tömma helt. 
- Skriv ett test som lägger till två element och sedan tar ut dem i rätt ordning. 
  (Du kan använda sammanslagning av strängar för att göra detta i en assert.)
- Kolla att den gamla länkade listan bara används för att skapa en iterator. (Listan behöver fortfarande ändras i `offer` och `poll`.)
- Kolla att alla testerna kör och är gröna.

## Del 3 Iterator

### Syfte
Förstå hur en iterator fungerar.

### Teknik
Implementera en iterator som en privat klass i `FifoQueue`.

### Filer att ändra i
- `FifoQueue.java`
- `FifoQueueTest.java` (flytta ett test till den nya testklassen)
- `FifoQueueIteratorTest.java` (Skapas)

### Instruktioner
- Skapa en `private class QueueIterator<E> implements Iterator<E>` i `FifoQueue`.
- Läs på om `Iterator` i dokumentationen.
- Ta helt bort medlemmen `list` i `FifoQueue`, returnera en instans av den nya iteratorn i `getIterator()`.
- Gör minsta möjliga ändringar i `QueueIterator` för att få befintliga tester att passera.
- Flytta testet av iteratorn från `FifoQueueTest.java` till `FifoQueueIteratorTest.java`.
- Implementera följande test på iteratorn, ett i taget, och få dem att passera:
  - `hasNext` returnerar `false` på tom lista.
  - `hasNext` returnerar `true` på en lista som inte är tom.
  - `next` kastar ett `NoSuchElementException`på en tom lista.
  - `next` returnerar värdet på nästa element, om det finns ett.
  - `next` ändrar inte på listan.
  - Skapa två iteratorer från samma lista, testa att de fungerar oberoende av varandra.
  

## Del 4 Slå ihop köer
Skriv funktionalitet som slår ihop två köer genom att använda 
den inre strukturen.

### Syfte
Öva på hur det går att utnyttja implementationsdetaljer när det behövs.

### Teknik
Använda det du vet om klassens interna struktur
Om du försöker lägga ihop en kö med sig själv ska funktionen kasta ett `IllegalArgumentException`.
Den andra kön ska vara tom när den lagts in i den första.

### Filer att ändra i
- `FifoQueue.java`
- `FifoQueueAppendTest.java`(Skapas)

### Instruktioner
Implementera en funktion som klarar att slå ihop följande. 
Koda testdrivet. Skapa en ny testklass. 
- två tomma köer
- tom kö som konkateneras till icke-tom kö
- icke-tom kö som konkateneras till tom kö
- två icke-tomma köer
- försök att slå ihop en kö med sig själv