### Referater
Referater er vedlagt i vår google disk: https://drive.google.com/drive/folders/1VpaGimflcbXHnR0sDl15mE1TTSsk2_fC 

### Roller
Vi har valgt å holde oss til den rollefordelingen vi har hatt frem til nå. Rollene er fortsatt fleksible og fordelt etter kapasitet og behov. Vi har aldri kjent på et behov for å ha distinkte roller i teamet, alle passer litt på hverandre og på at det vi leverer lever opp til forventningene våre. 

### Prosjektmetodikk
Når det kommer til prosjektmetodikk, har vi gått mer og mer bort fra kanban, og over til vår egendefinerte metodikk. Dette tror vi har å gjøre med at vi har jobbet sammen en stund nå, og har blitt kjent med hverandres arbeidsmåter, og naturligvis funnet vår egen flyt i måten vi arbeider på. Vi benytter oss fortsatt av project board på trello og parprogrammering med code with me. Her er vårt project board: https://trello.com/b/dbMlsUpA/roborally 

### Retrospektiv
Siden siste leveranse, har vi fokusert på forbedringspunktene vi satte for denne sprinten. Vi har blitt flinkere på å skrive tester til koden, samt å bruke git mer aktivt. Git har vært vesentlig mindre problematisk denne gangen. Dette tror vi er fordi vi har lært oss litt av hva som førte til problemene forrige gang, og funnet nye løsninger der vi unngår dette. 

Vi burde sette oss inn i hvilke krav vi har til en oppgave før vi begynner på oppgaven. Om alle har kontroll over forventningene vi har som team til produktet, så blir det enklere for alle å utføre arbeidet slik at det lever opp til disse forventningene. 

Vi merker også at vi ikke alltid har full oversikt over hva de andre på teamet holder på med, dette tror vi er fordi vi ikke bruker Trello i like stor grad. Det er nyttig å vite litt om hva alles arbeidsoppgaver er, så vi synes vi må bli bedre på å oppdatere project board på Trello før vi setter i gang med en ny arbeidsoppgave. 

### Gruppedynamikk og kommunikasjon
Det er vesentlig få forskjeller mellom forrige innlevering og nå, vi synes vi har en god gruppedynamikk der alle blir hørt og får sagt sine meninger. 

### Vektlegging av commits
Vi benytter oss fortsatt mye av parprogrammering, som gjør at fordelingen av commits ikke er jevn. Vi har committet en del på brancher, men disse har vi slettet ettersom de ikke trengs mer.

### Prioritering av ny funksjonalitet
Siden vi ikke har rukket å komme oss gjennom mvp, prioriterer vi oppgaver som omhandler disse. Vi har valgt å prioritere å få server på plass, samt mer grafikk på plass. Dette inkluderer kort og spillmeny, og funksjonalitet som tilhører dette. Vi arbeider også med å få til rundesystem i spillet. Sistnevnte har vi ikke fått til, og heller ikke grafikk for kortene når man spiller spillet. 

### Brukerhistorier
1. Som spiller ønsker jeg å se en meny når jeg starter spillet, slik at jeg kan få en oversikt over valgmulighetene jeg har før jeg begynner å spille.
  * Akseptansekriterier:
    * Gitt at jeg starter spillet, skal det komme en meny med tydelige valgmuligheter for å starte spillet, avslutte spillet, endre innstillinger osv
  * Arbeidsoppgaver:
    * Implementere meny


2. Som spiller ønsker jeg hinder i form av lasere på brettet, slik at jeg må planlegge hvor jeg vil bevege meg for å ikke havne i en laser.
  * Akseptansekriterier:
    * Gitt at det er en laser på brettet, skal jeg kunne visuelt se den, samt at den stråler ut i riktig retning. 
    * Gitt at laseren treffer en vegg eller spiller, skal den stoppe ved veggen eller spilleren.
    * Gitt at laseren treffer en spiller, skal spilleren registrere dette.
  * Arbeidsoppgaver: 
    * Grafikk og logikk for laser
 

3. Som spiller ønsker jeg å kunne spille mot andre på ulike maskiner, slik at det er konkurranse og jeg ikke spiller mot meg selv - ulike maskiner ikke implementert.
  * Akseptansekriterier:
    * Gitt at jeg spiller mot noen andre, skal de kunne spille på en annen maskin enn meg
  * Arbeidsoppgaver:
    * Server - Med en Client handler, som håndterer spiller bevegelse
    * Client - Oppdaterer etter server data
    * Payload - Data pakken som blir sendt mellom Server og Client.
    * Refactor det som er gjort til å bli kompatibelt med de nye endringene.
 

4. Som spiller ønsker jeg å se spillbrettet, kortene jeg har, life tokens osv når jeg starter å spille, slik at jeg hele tida kan ha oversikt over spillet. (ikke implementert enda)
  * Akseptansekriterier
    * Gitt at spillet er startet, skal spilleren kunne se alle elementer som trengs for å spille spillet i en skjerm. 
  * Arbeidsoppgaver
    * Sette sammen bakgrunn, spillbrett og grafikk for kort og andre spillelementer
    * Implementere GUI for disse spillelementene
 
### Testing
Vi har startet å skrive de manuelle testene fra forrige innlevering om til automatiske tester. Vi har også endret på example.tmx, så koordinatene i de manuelle testene fra sist vil ikke lenger være korrekte. De manuelle testene fra sist heter nå GameMechanicsManualTest.md. Vi har ikke rukket å skrive om alle testene enda, så vi har valgt å la md-filen bli selv om denne ikke er korrekt.
 
### Kjente bugs
Det er ikke lagt til innstillinger for spillet ennå, så når man trykker “preferences” på menyen, skjer det ingenting. 
Serveren kan dessverre fort krasje. Vi tror det er fordi programmet får for mye å gjøre. Vi har erfart at om man trykker på de forskjellige tastene langsomt så skal det gå fint.


