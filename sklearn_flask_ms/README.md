# Inteligentna spletna storitev z uporabo scikit-learn in Flask

## Konfiguracija

Implementacija storitve za svoje delovanje potrebuje vrednosti sistemskih spremenljivk, na podlagi storitev nalaga različne napovedne modele.

V ta namen je potrebno ustvariti nov tekstovno datoteko z imenom **.env**. Vsebina datoteke pa mora vsebovati zapise po sledečem vzoru (začasne vrednosti v primer spodaj, nadomestite s svojimi)

```
PROJECT="ime projekta na neptune.ai"
API_TOKEN="API_TOKEN platforme neptune.ai"
MODEL="ime ustvarjenega modela na neptune.ai"
STAGE="staging/production"
```

## Zagon

### Osnoven pristop

Za namestitev knjižnic potrebnih za izvajanje, v terminalu izvedite ukaz: ```pip install -r requirements.txt```

Za zagon spletne storitve v terminalu izvedite ukaz: ```python main.py``` ali ```python3 main.py```

### Z uporabo poetry

Za namestitev knjižnic potrebnih za izvajanje, v terminalu izvedite ukaz: ```poetry install```

Za zagon spletne storitve v terminalu izvedite ukaz: ```poetry run python main.py```

## Docker

### Izgradnja slike

Za izgradnjo docker slike, v terminalu izvedite ukaz: ```docker build -t sklearn-flask-ms .```

### Zagon vsebnika

Za zagon vsebnika zgrajene docker slike, v terminalu izvedite ukaz:
```
docker run -p 5000:5000 \
-e PROJECT=vase_ime_projekta \
-e API_TOKEN=vas_api_token \
-e MODEL=ime_modela \
-e STAGE=vas_stage \
sklearn-flask-ms
```