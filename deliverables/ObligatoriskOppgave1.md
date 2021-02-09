## Organisering av prosjektet
Den første tiden av prosjektet planlegger teamet blant annet å bli kjent med hverandre, holde jevnlige møter og sørge for at alle er med på alle steg i prosessen, 
slik at ingen blir glemt eller henger etter. 
Vi ble sammen enige om noen møtetider under den første gruppetimen, samt å alltid ha en referent på møter. Vi opprettet et repository, 
fikk lagd en felles google drive og lagd et project board nærmest med en gang etter prosjektstart. Deretter identifiserte vi rollene som trengs for dette prosjektet, 
og fordelte de rollene som lot seg fordele på et så tidlig stadie. 
Måten vi fordelte roller på var at den som var interessert og/eller har mest erfaring med en rolle meldte seg og tok på seg ansvaret. Til nå har vi satt opp 
rollene teamleder, testansvarlig og ansvarlig for server.
På hvert møte går vi også gjennom oppgavene vi har foran oss, og legger til flere oppgaver om det trengs. På møtene rapporterer hvert team-medlem til alle på 
teamet om hvilken oppgave de holder på med, hvordan det går, og om man eventuelt trenger hjelp til oppgaven sin. Deretter justerer vi fordeling av oppgaver etter 
tilbakemelding på hvert møte. Dermed vet vi alltid hva vi skal gjøre frem mot neste møte.

## Prosjektmetodikk
Prosjektmetodikken vi har valgt å prøve å følge er kanban, med elementer som parprogrammering og testing. Det er flere grunner til at vi har valgt denne metodikken. 
En av dem er at siden vi er så få på teamet, så kan vi operere mye på tillit og vi kan stille krav til disiplin. Vi liker også at metodikken er fleksibel, 
da vi også er det. Teamet har avgjort at møter er tiden for å planlegge mer arbeid, og vi har blitt enige om at 3 oppgaver er maksimum per person til enhver tid. 
 
## Brukerhistorier

* Som spiller ønsker jeg å kunne se hele brettet spillet foregår på. Slik at jeg kan se alle elementer på brettet inkludert brikker og hvor de beveger seg. 
	Løsningsbeskrivelse:

    * Akseptansekriterier:
      * Gitt at jeg spiller, så skal jeg se spillbrettet
      * Gitt at jeg spiller, så skal jeg se brikken min på spillbrettet


* Som spiller ønsker jeg et spill med hindringer slik at det kan være en utfordring og underholdende.
  * Løsningsbeskrivelse:
    * Det er nødvendig å ha hindringer i spillet slik at det er fristende å spille igjen
  * Akseptansekriterier:
    * Gitt at jeg ønsker en utfordring, vil jeg ha hinder i form av steder jeg ikke kan gå på brettet.


* Som spiller av RoboRally ønsker jeg å kunne se vinnermålet i spillet, slik at jeg kan se hvor jeg går for å vinne
  * Løsningsbeskrivelse:
    * det er nødvendig å ha et mål - i tilfellet av RoboRally, et flagg - som viser spilleren hva som skal til for å vinne. Dette må vises tydelig på brettet, og det burde ikke være noen forvirring om hvilket felt man må gå til for å vinne
  * Akseptansekriterier:
    * Gitt at jeg spiller, skal jeg kunne se flagget jeg må flytte meg til for å vinne.
    * Gitt at jeg spiller, skal jeg kunne tydelig se akkurat hvilken brikke jeg må flytte meg til for å vinne.

* Som spiller ønsker jeg å se at brikken flytter seg til riktig posisjon ettersom hva jeg taster, slik at det er samsvar mellom handlingene mine og det jeg ser.
  * Løsningsbeskrivelse:
    * Det er nødvendig for spilleren å hele tiden vite hvor den befinner seg på brettet, så om brettet ikke viser korrekt posisjon som følge av en handling kan det bli et vanskelig spill å spille. 
  * Akseptansekriterier:
    * Gitt at jeg spiller og trykker for å gå en retning, så skal jeg se at brikken på skjermen flytter seg i riktig retning og lander på rett sted. 

* Som spiller ønsker jeg at jeg visuelt kan se visse hendelser på brettet, slik som uavgjort og seier, slik at jeg vet når spillet er slutt.
  * Løsningsbeskrivelse: 
    * det er nødvendig å få en beskjed om spillet er endt, sånn at man vet når man skal stoppe å spille, og at spilleren gjennom hele spillet føler de vet hva situasjonen i spillet er.
  * Akseptansekriterier:
    * Gitt at jeg vinner spillet, så skal jeg få en visuell beskjed om det.
    * Gitt at jeg taper spillet, så skal jeg få en visuell beskjed om det. 
    * Gitt at det blir uavgjort, så skal jeg få en visuell beskjed om det. 
