## Organisering av prosjektet
Den første tiden av prosjektet planlegger teamet blant annet å bli kjent med hverandre, holde jevnlige møter og sørge 
for at alle er med på alle steg i prosessen, slik at ingen blir glemt eller henger etter. 

Vi ble sammen enige om noen møtetider under den første gruppetimen, samt å alltid ha en referent på møter. Vi opprettet 
et repository, fikk lagd en felles google drive, lagd et project board og fått satt opp en discord nærmest med en gang 
etter prosjektstart. Vi delte også annen kontaktinformasjon slik at vi kan få tak i hverandre lettere.
Deretter identifiserte vi rollene som trengs for dette prosjektet, og fordelte de rollene som lot 
seg fordele på et så tidlig stadie. Vi har valgt å bruke Trello project board for å holde styr på oppgavene. Vi 
fargekoder etter type oppgave, og legger til members på hver oppgave etter hvem som utfører/skal utføre oppgaven.
Her er brettet vårt: https://trello.com/b/dbMlsUpA/roborally. Brukerhistoriene finner man i beskrivelsen til hvert 
enkelt gjøremål når du trykker deg inn på gjøremålet.

Alle på teamet har nokså lik kompetanse. Vi har alle tatt INF100, INF101, INF102 og INF122. Utenom dette har to på 
teamet litt ekstra erfaring. Micah har erfaring fra web utvikling i jobbsammenheng og har derfor noe erfaring med å 
jobbe i teams. Sondre har erfaring med prosjektarbeid, møtestruktur, kursing i prosjektstruktur og teamarbeid.

Måten vi vil fordele roller på er at den som er interessert og/eller har mest erfaring med en rolle melder seg og tar 
på seg ansvaret. Vi planlegger å tildele rollene teamleder, testansvarlig og ansvarlig for server. Vi tenker å gi Micah 
team-leader-rollen fordi han er den på gruppen som har mest erfaring med å jobbe team innenfor IT. Når det kommer til 
de resterende rollene fordeler vi etter interesse. Vi har satt rollene til å være veldig fleksible, så vi hjelper 
hverandre i alle roller dersom hjelp trengs.

På hvert møte går vi også gjennom oppgavene vi har foran oss, og legger til flere oppgaver om det trengs. På møtene 
rapporterer hvert team-medlem til resten av teamet om hvilke(n) oppgave(r) de holder på med, hvordan det går, og om man 
eventuelt trenger hjelp til oppgaven sin. Deretter justerer vi fordeling av oppgaver etter tilbakemelding på hvert møte. 
Dermed vet vi alltid hva vi skal gjøre frem mot neste møte og sørger for at alle har noe å gjøre samt får til å gjøre 
det de har fått utdelt. Hyppige møter er også måten vi følger opp hverandres arbeid. På hvert møte har vi også en 
referent. Hen skriver hva som bestemmes på møtene og laster de opp i vår google drive. På denne måten har vi alltid 
oversikt over hva vi ble enige om på møtene. Her er link til vår google drive, referatene ligger her:
https://drive.google.com/drive/folders/1VpaGimflcbXHnR0sDl15mE1TTSsk2_fC?usp=sharing


## Prosjektmetodikk
Prosjektmetodikken vi har valgt å prøve å følge er kanban, med elementer som parprogrammering og testing. Det er flere 
grunner til at dette er metodikken vi ønsker å følge. En av dem er at siden vi er så få på teamet, så kan vi operere mye 
på tillit og vi kan stille krav til disiplin. Vi liker også at metodikken er fleksibel, da vi også er det. Som studenter 
ser hver uke forskjellig ut, og da er det greit å kunne justere måten vi arbeider på etter behov. Teamet har avgjort at 
møter er tiden for å planlegge mer arbeid, og vi har blitt enige om at 3 oppgaver er maksimum per person til enhver tid. 
I tillegg syns vi mengden regler som inngår i de andre metodikkene vi har gått gjennom ble overflødig når teamet er såpass 
lite, og alle har omtrent samme kompetanse.
 
## Spesifikasjon
Det overordnede målet for applikasjonen å få på plass de grunnleggende strukturene og funksjonalitetene i spillet.

### Prioritert liste over brukerhistorier:
(Mal fra Entur: https://design.entur.org/kom-i-gang/for-designere/brukerhistorier)

1. Som spiller ønsker jeg å kunne se brettet spillet foregår på. Slik at jeg kan se alle elementer på brettet inkludert 
   brikker og hvor de beveger seg.
    * Akseptansekriterier:
      * Gitt at jeg spiller, så skal jeg se spillebrettet.
      * Gitt at jeg spiller, så skal jeg se brikken min på spillebrettet.
   * Arbeidsoppgaver:
      * GUI for brett
      * Lage logisk modell for brett


2. Som spiller ønsker jeg å se at brikken flytter seg til riktig posisjon ettersom hva jeg taster, slik at det er samsvar 
   mellom handlingene mine og det jeg ser.
   * Akseptansekriterier:
      * Gitt at jeg spiller og trykker for å gå en retning, så skal jeg se at brikken på skjermen flytter seg i riktig 
        retning og lander på rett sted.
   * Arbeidsoppgaver:
      * Tydelig grid-oppset
      * Grafikk for spiller
      * Logikk for bevegelse av robot på brettet


3. Som spiller ønsker jeg at jeg visuelt kan se når jeg har vunnet eller tapt, slik at jeg vet når spillet er over
   * Akseptansekriterier:
      * Gitt at jeg vinner spillet, så skal jeg få en visuell beskjed om det.
      * Gitt at jeg taper spillet, så skal jeg få en visuell beskjed om det.
   * Arbeidsoppgaver:
      * Grafikk og logikk for når spiller har vunnet
      * Grafikk og logikk for når spiller dør


4. Som spiller av RoboRally ønsker jeg å kunne se vinnermålet i spillet, slik at jeg kan se hvor jeg går for å vinne
   * Akseptansekriterier:
      * Gitt at jeg spiller, skal jeg kunne se flagget jeg må flytte meg til for å vinne.
      * Gitt at jeg spiller, skal jeg kunne tydelig se akkurat hvilken brikke jeg må flytte meg til for å vinne.
   * Arbeidsoppgaver:
      * Sette inn grafikk og logikk for flagg


5. Som spiller ønsker jeg at det registreres når roboten detter ned i et hull.
   * Akseptansekriterier:
      * Gitt at jeg spiller og roboten havner over et hull, skal jeg visuelt kunne se det.
   * Arbeidsoppgaver:
      * Grafikk og logikk for hull

Vi har testet koden ved å kjøre den og sett at alt fungerer som forventet.

## Oppsummering

Vi strukturerte oss godt fra starten av. Var flinke på oppgavefordeling, oppfølgning og å ha jevnlige møter.
Vi har for det meste brukt discord til å kommunisere og dette har fungert veldig godt for at alle får sine meninger hørt.
De strukturelle og organisatoriske aspektene ved prosjektet tror vi fungerte godt fordi vi kommuniserer bra og passer på 
at alle henger med og er inkludert i arbeidet til enhver tid.

Å vise at vi sammarbeidet godt fungerte ikke helt som forventet. Vi har brukt mye code with me og google docs for å 
sammarbeide. Dermed kan man ikke se gjennom våre commits at vi har sammarbeidet godt og at alle har bidratt mer eller 
mindre på lik linje til prosjektet.

En ønskelig forbedring til neste gang er at vi skal bruke git mer aktivt og effektivt. Vi har et ønske om å øke felles 
kompetanse og bli mer komfortabel med egen git-bruk. Vi ønsker å gjøre dette med å la noen av team-medlemmene bruke mer 
tid på å lære seg git slik at de kan bistå til hjelp ved usikkerhet og ta ansvar for å lære det videre til de andre 
team-medlemmene og ta ansvar for å lære det videre til de andre team-medlemmene.

Vi planla å følge prosjektmetodikken kanban, men det har ikke vært spesielt sentralt i hvordan vi har jobbet med 
oppgaven. Vi har likevel inkludert mye av det kanban går ut på, som at vi har passet på at ingen jobber med flere 
oppgaver enn de har kapasitet til. Vi gikk også litt bort fra den planlagte rollefordelingen vår, og gikk heller over 
til å fordele basert på tillit og tilgjengelighet.

Vi syns vi traff godt på oppgaven, både når det kom til å jobbe i team og å få utført alle arbeidsoppgavene. Hele 
prosessen opplevdes som oversiktlig og ryddig fra start til slutt. Alle var flinke til å møte opp til avtaler og gjøre 
sin del av arbeidet. 

