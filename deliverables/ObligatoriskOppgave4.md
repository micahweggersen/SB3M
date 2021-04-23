### Referater
Referatene ligger på vår google drive som du finner her: https://drive.google.com/drive/folders/1VpaGimflcbXHnR0sDl15mE1TTSsk2_fC 

### Roller
Vi har ikke gjort noen endringer i rollene siden sist. Vår fleksible rollefordeling fungerer bra for teamet, og vi har derfor valgt å fortsette med dette.

### Prosjektmetodikk
Prosjektmetodikken vår har ikke endret seg noe særlig siden sist. Vi følger fortsatt vår egendefinerte metodikk. Dette har fungert bra. Vi benytter oss fortsatt av project board på trello og parprogrammering, enten fysisk eller gjennom code with me. Et mulig forbedringspunkt fremover kan være at vi må bli flinkere til å oppdatere vårt project board jevnlig. Vi har en tendens til å skrive ferdig brukerhistorier til oppgaver først etter at vi er ferdig med oppgaven. Alt i alt syns teamet at vi har tatt gode valg, og det er lite vi ønsker å endre på når det kommer til metodikk. Her er vårt project board: https://trello.com/b/dbMlsUpA/roborally 

### Retrospektiv 
Slik som sist, har vi denne gangen fokusert på forbedringspunktene vi nevnte i siste leveranse. Vi startet denne sprinten med et møte hvor vi gikk gjennom hvilke krav og oppgaver vi hadde foran oss. Deretter ble vi enige om et realistisk mål for hva vi kom til å klare å få gjort i løpet av denne sprinten. Som nevnt tidligere, ble project board brukt litt lite sist leveranse. Denne gangen har vi forsøkt å oppdatere dette mer jevnlig. Det ble likevel litt mye rydding der inne i siste liten, men vi har helt klart forbedret oss på dette punktet.

Alt i alt syns vi prosjektet har gått bra. Vi har vært flinke til å fordele arbeid og å ikke ta på oss mer ansvar enn vi har kapasitet til. Oppmøte på møtene har også vært gode. Møtene har også vært effektive og oversiktlige.

Om vi hadde startet på nytt ville vi blant annet strukturert koden annerledes med tanke på server, og hatt flere personer til å jobbe med serveren slik at kunnskapen om hvordan den brukes var kjent i større deler av teamet. Da hadde vi sannsynligvis brukt mindre tid på å hjelpe hverandre med server-ting.

Serveren viste seg å være svært viktig for resten av spillet, som gjorde at problemer med kommunikasjonen mellom server og client vil prege resten av systemet, som har ført med seg mye tekniske utfordringer og mange hyppige krasj av systemet. Dette har gjort at å lime sammen runde-logikken har vært problematisk. 

Vi synes også vi delte oss litt mye og hadde litt faste personer vi jobbet med når vi arbeidet med prosjektet. Vi endte med at noen jobbet mye med backend, mens andre jobbet mye med frontend. Dette gjorde at vi endte med at vi visste lite om hvordan det vi ikke hadde laget selv fungerte, og da var det vanskelig å hjelpe hverandre, og utvikle programmet vårt sammen videre.

Vi ville også distribuert tiden vår litt bedre, slik at vi ikke hadde trengt å jobbe veldig mye de siste dagene før innleveringene. Det har forsåvidt gått fint, men det hadde vært bedre om det ikke var så hektisk som det har vært mot slutten av sprintene.

### Gruppedynamikk og kommunikasjon
Gruppedynamikken og kommunikasjonen er nokså lik som den alltid har vært. Det er lav terskel for å ta kontakt med hverandre og spørre om ting på discord. Nå mot slutten har vi blitt enda flinkere til å hjelpe hverandre og samarbeide utenom møtetider. Vi benytter oss mye av discorden vår til å videochatte, dele skjerm, og å hjelpe hverandre. 

### Prioritering av ny funksjonalitet
Vi ønsker å prioritere de oppgavene vi ikke klarte å fullføre sist gang. Vi rakk ikke å sette sammen et fullstendig spillebrett, samt å få lasere til å fungere ordentlig, dette har blitt prioritert denne gangen.

### Brukerhistorier
1. Som spiller ønsker jeg å se kortene i tillegg til spillebrettet på skjermen når jeg starter et spill. 
* Akseptansekriterier:
  * Gitt at spillet er startet, og man har trykket “D”, skal kortene man har å velge blant vises på skjermen.
  * Gitt at spiller har valgt kortene sine, skal disse også vises på skjermen, på en annen lokasjon enn der kortene man kunne velge blant ble vist. 
* Arbeidsoppgaver:
  * Implementere GUI for kort
  * Sette sammen en skjerm bestående av både spillebrett og visualisering av kort.

2. Som spiller ønsker jeg å se riktig antall life tokens på skjermen til enhver tid mens et spill pågår.
* Akseptansekriterier:
  * Gitt at et spill er startet, skal livene mine i form av life tokens vises på skjermen. 
  * Gitt at spiller mister et liv, skal antall life tokens minskes med én, og dette skal vises på skjermen ved at et life token forsvinner. 
* Arbeidsoppgaver: 
  * Implementere GUI for liv. Plassere grafikken korrekt.
  * Kombinere logikk og grafikk for liv.

3. Som spiller ønsker jeg å kunne se hvor mye skade jeg har påtatt visuelt på skjermen til enhver tid i spillet. 
* Akseptansekriterier:
  * Gitt at et spill er startet, skal posisjonene for damage tokens være plassert korrekt over valgte kort.
  * Gitt at jeg påtar meg en skade, skal antall damage tokens oppdateres, og også vises på skjermen. 
  * Gitt at spiller har mottatt 10 damage tokens, så skal disse nullstilles, og spiller skal miste et liv.
* Arbeidsoppgaver:
  * Implementere GUI for skade i form av damage tokens.
  * Kombinere logikk og grafikk for skade.
  * Kombinere logikk og grafikk for damage tokens og life tokens.
 
4. Som spiller ønsker jeg at interagering med lasere på brettet registreres og vises på skjermen.
* Akseptansekriterier:
  * Gitt at en spiller går inn i en laser, skal spiller motta en damage token.
* Arbeidsoppgaver: 
  * Knytte sammen logikk for laser med logikk og grafikk for damage tokens. 

5. Som spiller ønsker jeg å kunne spiller med flere spillere. 
* Akseptansekriterier:
  * Gitt at et spill har startet, skal det være mulig å bli med på det spillet med en annen spiller. 
  * Gitt at et spill har to spillere, skal man kunne se forskjell på robotene til spillerne.
* Arbeidsoppgaver:
  * Starte server
  * Starte client
  * Farge spillere

6. Som spiller ønsker jeg at når spillfiguren min står på et brett objekt (ikon) så skal oppførselen tilsvare ikonet på brettet.
* Akseptansekriterier:
  * Gitt at spillfiguren er over et objekt på brettet, skal oppførselen samsvare med objektet på brettet. 
* Arbeidsoppgaver:
  * Implementere stasjoner for å reparere roboten
  * Implementere pushers som dytter roboten
  * Implementere auto walks, som transportbånd som transporterer roboten
  * Implementere rotasjonshjul

### Testing
Manuelle tester for screens og tegning av elementer på skjermen ligger i test-mappen sammen med alle de andre testene. Filen heter ScreensManualTest.md. Vi skrev et par automatiske tester for ny spillerlogikk, men det var noe som ikke lot seg teste så enkelt, som for eksempel lasere. Vi kompenserte for dette ved å ha manuelle tester for laserne. 

### Kjente bugs
* Etter at man har valgt kort blir disse tegnet opp fra høyre til venstre, som er lite intuitivt. Vi har lagt inn tall under disse plassene for å tydeliggjøre hvilken rekkefølge de spilles i.
* Når alle fem kort er valgt og man taster “S” for å spille diss, forsvinner alle kortene fra skjermen. Dermed kan man ikke se dem lenger, men de er fortsatt valgt og kan spilles. 
* Når man spiller med kort, er det ikke alltid skade registreres når roboten går inn i en laser. Dette er ikke et problem om man beveger seg med piltaster.
* Når man beveger seg over et hull, og ikke stopper på det, mister man ikke lifetoken. Dette ble oppdaget litt for sent. 

### Vektlegging av commits
Vi har brukt code with me ganske mye. Vi prøver å huske på å oppgi hvem som har kodet i committen når vi benytter oss av dette, men i github vil det fortsatt se ut som om kun den ene har committet. Det er ikke alltid dette ble husket på, som kan føre til skjevfordeling av commits. 





