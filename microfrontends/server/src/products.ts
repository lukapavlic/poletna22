export interface Product {
  id: number;
  name: string;
  price: number;
  description: string;
  image: string;
  longDescription: string;
}

const products: Product[] = [
  {
    id: 1,
    name: 'Mladi voznik',
    price: 1,
    description: 'Sklenite zavarovanje za mlade voznike, nevezano na avto.',
    image: 'http://localhost:8080/zav-1.jpg',
    longDescription:
      'V kategorijo »mladi voznik« spadate vsi tisti, ki imate manj kot tri leta vozniških izkušenj (šteto od prve pridobitve vozniškega dovoljenja) kot tudi kandidati za voznika, ki vozite vozilo s spremljevalcem. Zavarovanje »MladiVoznik« je samostojno zavarovanje, vezano na voznika in ne na osebno vozilo. S tem zavarovanjem lahko brezskrbno vozite katerokoli osebno vozilo, npr. očetovo, mamino, babičino ali prijateljevo vozilo, lastnikom teh vozil pa ni potrebno plačevati dodatka zaradi povečane nevarnosti mladega voznika. Zavarovanje »MladiVoznik« finančno razbremeni lastnika vozila, s katerim mladi voznik povzroči prometno nesrečo, za soudeležbo pri uveljavljeni škodi iz: - zavarovanja avtomobilske odgovornosti (AO), - zavarovanja telesnih poškodb voznika (AO+) in - zavarovanja avtomobilskega kaska (AK).',
  },
  {
    id: 2,
    name: 'Zavarovanje Naše tačke',
    price: 1,
    description: 'Zavarujte pse in mačke z novim zavarovanjem po vaši meri.',
    image: 'http://localhost:8080/zav-2.jpg',
    longDescription:
      'Psi in mačke v naš dom prinašajo veselje in nas osrečujejo. Kot vsa živa bitja pa so žal tudi oni izpostavljeni različnim nevarnostim. Zgodi se, da zbolijo ali se poškodujejo in takrat je najmanj, kar lahko storimo zanje, da jim namenimo kvalitetno strokovno pomoč in nego. Za vas smo zato pripravili novo zavarovanje za pse in mačke po vzoru najsodobnejših zavarovanj na svetovnem trgu.',
  },
  {
    id: 3,
    name: 'Nezgodno zavarovanje otrok in mladine',
    price: 1,
    description: 'Zavarujte svoje otroke z nadomestili za primere nezgode.',
    image: 'http://localhost:8080/zav-3.jpg',
    longDescription:
      'Najlepše je opazovati svojega otroka, kako raste, se razvija in se spreminja v odgovorno osebo. Vloga vsakega starša ali skrbnika je, da ga na izbrani poti spodbuja in mu zagotovi varno ter brezskrbno doseganje zastavljenih ciljev.',
  },
  {
    id: 4,
    name: 'Dodatno zdravstveno zavarovanje Zdravje',
    price: 1,
    description: 'Enostaven in hiter dostop do zdravstvenih storitev.',
    image: 'http://localhost:8080/zav-4.jpg',
    longDescription:
      'Z zdravstvenim zavarovanjem vam v primeru novo nastale bolezni, stanja ali poškodbe, za katero ste v času jamstva pridobili zdravniško napotilo, omogočamo enostavnejši in hitrejši dostop do zdravstvenih specialističnih storitev ali asistenčnih storitev ter zdravljenja brez čakalnega roka.',
  },
  {
    id: 5,
    name: 'Letno turistično zavarovanje z asistenco v tujini',
    price: 1,
    description: 'Za velike popotnike, ki radi potujejo brez skrbi.',
    image: 'http://localhost:8080/zav-5.jpg',
    longDescription:
      'Na počitnicah v tujini vso svojo pozornost namenite turističnim znamenitostim in se prepustite utripu okolja. Pred potovanjem poskrbite za turistično zavarovanje z asistenco ter skrbi prepustite nam in našim strokovnjakom v asistenčnem centru.',
  },
  {
    id: 6,
    name: 'Kratkoročno turistično zavarovanje z asistenco v tujini',
    price: 1,
    description: 'Za izletnike, ki skrbi raje pustijo doma.',
    image: 'http://localhost:8080/zav-6.jpg',
    longDescription:
      'Na počitnicah v tujini vso svojo pozornost namenite turističnim znamenitostim in se prepustite utripu okolja. Pred potovanjem poskrbite za turistično zavarovanje z asistenco in skrbi prepustite nam in našim strokovnjakom v asistenčnem centru.',
  },
  {
    id: 7,
    name: 'Zavarovanje rizika odpovedi potovanj',
    price: 1,
    description: 'Zavarujte riziko odpovedi potovanj.',
    image: 'http://localhost:8080/zav-7.jpg',
    longDescription:
      'Kaj če se zaradi bolezni, nezgode ali kakšnega drugega nepredvidenega dogodka ne boste mogli udeležiti težko pričakovanega potovanja? Svetujemo vam, da že pri načrtovanju potovanja poskrbite, da se takšne in drugačne nevšečnosti rešijo s čim manj zapleti. Počitnice, potovanja, izleti, koncerti, konference … naj ostanejo v spominu kot kolaž prijetnih dogodkov.',
  },
  {
    id: 8,
    name: 'Zavarovanje mikromobilnosti',
    price: 1,
    description:
      'Zavarujte kolesa, e-kolesa, skiroje, rolke in sebe za primere neljubih dogodkov.',
    image: 'http://localhost:8080/zav-8.jpg',
    longDescription:
      'Eno in celovito zavarovanje za vse vaše poti, ki jih opravite z mikromobilnimi sredstvi na območju Evrope. Zavarovanje je pri rizikih odgovornosti, asistence in nezgode vezano na osebo in ne na prevozno sredstvo, pomeni da ima zavarovanec kritje za katerokoli mikromobilno prevozno sredstvo, ki ga uporablja. Z družinsko polico lahko zavarujete tudi ožje družinske člane, ki se prevažajo z njim.',
  },
];

export default products;
