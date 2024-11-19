
# Ledtrådar och bakgrund
(Version 0.1)

## Del 1, Delegera till en befintlig struktur
Klassen du skapar ska ha följande signatur:
```
public class FifoQueue<E> extends AbstractQueue<E>
```
Att `implements Queue<E>` inte behövs i klassrubriken beror på att `AbstractQueue` implementerar `Queue`.
Den egenskapen ärvs av `FifoQueue`.

Förklaring till varför klassen `FifoQueue` ärver `AbstractQueue`: 
Ibland kan man implementera vissa metoder med hjälp av andra metoder. 
Ex:
```
public boolean isEmpty () {
return size() == 0;
}
```

För att underlätta för den som ska implementera det (stora) interfacet `Queue` finns den abstrakta klassen `AbstractQueue`.
Den innehåller många av `Queue`-metoderna implementerade enligt detta mönster. 
Det som återstår att göra i klassen `FifoQueue` är att implementera metoderna `offer`, `size`,`peek`, `poll` och `iterator`.

````		
/**
* Inserts the specified element into this queue, if possible.
* post: the specified element is added to the rear of this queue.
* @param x the element to insert
* @return true if it was possible to add the element to this queue, else false
*/
boolean offer(E x);

/**
* Returns the number of elements in this queue.
* @return the number of elements in this queue
*/
int size();

/**
* Retrieves, but does not remove, the head of this queue,
* or returns null if this queue is empty.
* @return the head of this queue, or null if the queue is empty
*/
E peek();

/**
* Retrieves and removes the head of this queue,
* or returns null if this queue is empty.
* post: the head of the queue is removed if the queue was not empty
* @return the head of this queue, or null if the queue is empty
*/
E poll();

/**
* Returns an iterator over the elements in this queue.
* @return an iterator over the elements in this queue
*/
Iterator<E> iterator(); 	
````

Varje metod ska bara vara en rad.
Läs dokumentationen för `Queue` om du är osäker på vilken.
Tänk på att fifo betyder "first in first out", alltså, först in först ut.

### Om metoden `offer`
Enligt specifikationen ska det inskickade elementet bara sättas in om det är möjligt.
I Java-dokumentationen av interfacet `Queue` står det att det är tillåtet att införa begränsningar på köer,
t ex att en kö bara får innehålla ett visst antal element. 
Om man anropar metoden `offer` när kön redan innehåller det maximalt tillåtna antalet,
ska ingen insättning göras och metoden ska returnera `false`.
I vår implementering ska inte någon sådan begränsning göras. 
Metoden ska därför i klassen `FifoQueue` alltid sätta in elementet och returnera `true`.


## Del 2, skriv en egen kö
Ja, du får ha ett attribut som räknar upp `size` vid insättning,
och minskar det när du tar ut ett element.

### Om länkade listor

Ett stätt att lagra data är att lägga varje objekt i en nod och sedan koppla ihop dem på olika sätt.
Det kan vara en graf, ett träd, eller i enklaste fallet, en länkad lista.
Listan som vi använder i den här labben är dessutom enkel-länkad och rund-länkad.

I en länkad lista så har själva listan bara koll på ett par noder, i det här fallet bara den sista.
I övrigt så håller noderna ordning på varandra.
Noder i enkellänkade listor har en referens till en annan nod.
Dubbellänkade listor har referens till föregående, och till nästa, i varje nod.
I en hopp-lista går det att ha referenser till noder flera steg fram, eller bak, 
så att det går att hoppa längre sträckor i listan med bara ett hopp.

En del länkade listor är konstruerade så att det finns en specifik nod, 
`head` som bara har som uppgift att hålla ordning på den första och sista noden.
 I en rund-länkad lista så refererar sista noden till första, eller första till sista,
beroende på vilket håll länkningen går, och listan har referens till en nod som också har ett värde i sig. 

Utifrån har alla länkade listor en början och ett slut, 
det är i implementationen som de skiljer sig åt.

Om listan är tom i en lista som vår, så ska referensen till en nod peka på `null`.
I en lista med en huvud-nod kommer dess referenser peka på den själv om listan är tom.

### Ändra i en länkad lista, sätta in och ta ut element.
När ett element sätts in, eller tas ut, i en länkad lista behöver vi flytta om referenserna så att alla 
noder refererar rätt.
Om det är svårt att tänka hur det fungerar kan det hjälpa att rita ut noderna. 

Fundera på vilka olika noder som är inblandade.

Rita gärna, både hur det ser ut före och efter.

Lista vilka noder du kommer behöva ha en referens till.

Klura ut i vilken ordning du bäst får tag i dem.

Fundera på om du kan använda listans referens till en nod.

När du har alla kan du koppla ihop dem på rätt sätt.

Glöm inte att det sista elementet har det första som sin next.

Du behöver inte göra en snygg lösning, speciellt inte som ditt första försök.
När du väl har något som får testerna att gå igenom kan du ändra runt i koden, 
men då direkt få återkoppling om än ändring har sönder något.
En omständlig lösning som fungerar går att fixa till, 
det viktiga är att första versionen av koden hjälper dig att förstå problemet.

### Om nästlade klasser
En nästlad klass är en klass som skrivs innanför de yttersta måsvingarna `{` `}` på en annan klass.
Den ligger i den yttre klassen.
Nästlade klasser som är statiska kallas för statiska nästlade klasser.
Nästlade klasser som inte är statiska kallas för inre klasser.

Listan kommer att ha två nästlade klasser när den är klar, 
en som är en statisk och en som är en inre klass. 
Skillnaden är att en statisk klass inte har tillgång till den inre strukturen hos
klassen den ligger i. 
En inre klass, som inte är statisk, kan komma åt instansvariabler i den klass där den skapas.

Tänk på det som att den inre klassen faktiskt i inne i den andra klassen, 
medan den statiska nästlade klassen mest ligger där för att vara gömd från omvärlden.

Noderna behöver inte kunna komma åt något utanför sig för att fungera.
Därför ska Nod-klassen vara statisk.
En iterator kan använda att den kan komma åt attribut i klassen den ligger i.
Då ska den inte vara statisk. 

### Noden

```
private static class QueueNode<E>  {
E element; 			// refererar till elementet
Node<E>  next;	// refererar till efterföljande nod

		/* Konstruktor */
		Node(E element) {
			this.element = element;
			next = null;
		}
	}
```



## Del 3 Iterator
Använd listans `last` för att se om du har kommit till slutet eller inte.
Skalet till iterator-klassen.
Den ska ligga i `FifoQueue`.
Konstruktorn kan vara privat, för den ska bara användas i den yttre klassen.

```
public class FifoQueue<E>  extends AbstractQueue<E>  {
	...	
	public Iterator<E> iterator () {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		...
		/* Konstruktor */
		private QueueIterator() {...}
				
		public boolean hasNext() {...}
				
		public E next() {...}	
	}
}
```

Test för att kolla att något kastar ett exception kan skrivas så här:
```		
assertThrows(NoSuchElementException.class, () -> iterator.next());
```
Där det andra argumentet är ett lambda.

## Del 4 Slå ihop köer

### Om synlighet
Nod-klassen är inte synlig utanför vår kö-klass,
men i metoder i kö-klassen går den att komma åt.
Även privata medlemmar går att komma åt i alla nästlade klasser.


```
/**
* Appends the specified queue to this queue
* post: all elements from the specified queue are appended
* to this queue. The specified queue (q) is empty after the call.
* @param q the queue to append
* @throws IllegalArgumentException if this queue and q are identical
*/
public void append(FifoQueue<E> q);
```

I kommentaren står att `IllagalArgumentException` ska genereras som queue och q1
är identiska. Med det menas att man inte ska kunna slå ihop en kö med sig själv.


