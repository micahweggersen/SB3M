### Roller
Vi har valgt å fortsette med de rollene vi lagde sist. Rollene har fungert bra, og ingen på teamet ønsker noe endring her. I tillegg syns vi rollene vi har til nå er tilstrekkelige, og derfor har vi bestemt oss for å ikke dele ut nye ansvarsområder. Vi på teamet er fortsatt veldig fleksible, og foretrekker å ta på oss ansvar der det trengs, fremfor å la bestemte roller sette grenser for hva vi kan gjøre. 

### Prosjektmetodikk
Til å starte med ønsket vi å følge metodikken kanban, men vi endte med en metodikk basert på tillit og tilgjengelighet, men kanban forblir fortsatt grunnlaget for vår metodikk. Dette ønsker vi å fortsette med da det fungerer godt for oss. Vi passer fortsatt på at ingen jobber med flere oppgaver enn de har kapasitet til, og bruker project boardet på trello og parprogrammering flittig. Hittil har denne metodikken fungert bra for teamet. Det er klart og tydelig hvilke oppgaver vi har foran oss, og hvem som jobber med hva til enhver tid. 

### Gruppedynamikk og kommunikasjon
Dynamikken i gruppen er god, alle blir hørt og respektert. Vi kjenner hver vår plass i gruppen og hvilke arbeidsoppgaver vi selv har. Disse jobber vi så med hver for oss, eller sammen dersom oppgaven er krevende. Vi kommuniserer godt på møtene, da vi rapporterer til hverandre, stiller spørsmål, og tar opp eventuelle saker. 

### Vektlegging av commits
Fordelingen av commits er jevnere fordelt denne gangen enn sist, men vi benytter oss fortsatt mye av par-programmering, både fysisk og via code with me, som kan bidra til en skjevfordeling av commits. 

### Referater
Alle referatene ligger på vår google drive: https://drive.google.com/drive/folders/1VpaGimflcbXHnR0sDl15mE1TTSsk2_fC?usp=sharing 

### Brukerhistorier
1. Som spiller ønsker jeg å kunne spille mot andre på ulike maskiner, slik at det er konkurranse og jeg ikke spiller mot meg selv
  * Akseptansekriterier:
    * Gitt at jeg spiller mot noen andre, skal de kunne spille på en annen maskin enn meg
  * Arbeidsoppgaver:
    * Server
 
2. Som spiller ønsker jeg at spillet deler ut riktig mengde kort til meg, slik at jeg kan spille etter spillereglene for kort
  * Akseptansekriterier:
    * Gitt at det er starten på en ny runde, skal kortene deles ut og bli synlige for spillerne.
    * Gitt at jeg har en skade, skal antall kort utdelt til meg være mindre enn om jeg ikke har en skade
    * Gitt at jeg har x antall skader, skal jeg få tildelt x antall færre kort enn ni kort.
  * Arbeidsoppgaver:
    * GUI for kort
    * Antallslogikk for utdeling
    * Utdelingslogikk

3. Som spiller ønsker jeg at jeg kan velge og bestemme rekkefølge på kortene som jeg vil, for å lage best mulig strategi for å vinne
   * Akseptansekriterier:
    * Gitt at jeg skal til å velge kort, må jeg velge antall lovlige kort.
    * Gitt at jeg har fem kort så skal jeg velge rekkefølgen de har. 
  * Arbeidsoppgaver
    * Skadelogikk
    * Velge riktig kort
    * GUI for kort
 
4. Som spiller ønsker jeg at roboten skal bevege seg etter verdiene på kortet, og etter rekkefølgen jeg som spiller valgte ut fra utdelte kort, for å bevege roboten nærmest mulig ønsket posisjon og unngå hinder på brettet. 
  * Akseptansekriterier:
    * Gitt at jeg har valgt et kort, skal roboten bevege seg ut i fra informasjonen på kortet.
  * Arbeidsoppgaver:
    * 

### Arbeidsfordeling
Vi fordelte oss i grupper og begynte med de oppgavene vi mente var viktigst å bli ferdig med først, og jobbet oss nedover listen. Vi prioriterte oppgavene i en rekkefølge slik at vi får best mulig arbeidsflyt. Både for at det skal gå an å dele ut kort, og bevege spilleren ut ifra kortene, er det nødvendig å ha de forskjellige kortklassene opprettet og velfungerende. Derfor implementerte vi dem først slik at vi skulle slippe å stoppe midt i en annen oppgave og gå tilbake til det.

Beatrix, Maria og Malin begynte med oppgaven å lage klassene vi trenger for kort, etter hvert hjalp hele gruppa til med denne oppgaven. Malin skulle også tegne kortene slik at vi kan ha dem visuelt på brettene også. Micah fikk oppgaven å opprette en server slik at vi etter hvert kan spille fra flere maskiner. Sondre fikk oppgaven å bevege spilleren ut ifra kortene. 
