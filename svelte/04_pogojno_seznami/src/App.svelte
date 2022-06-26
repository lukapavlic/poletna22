<script>
  import Stik from "./Stik.svelte";

  let ime = "Max";
  let poklic = "";
  let opis = "";
  let formState = "empty";
  let novStik;

  let stiki = [];

  function dodajStik() {
    if (
      ime.trim().length == 0 ||
      poklic.trim().length == 0 ||
      opis.trim().length == 0
    ) {
      formState = "invalid";
      return;
    }
    formState = "done";
    novStik = { id: Math.random(), ime: ime, poklic: poklic, opis: opis };
    stiki = [...stiki, novStik];
  }

  function izbrisiPrvega() {
    stiki = stiki.slice(1);
  }

  function izbrisiZadnjega() {
    stiki = stiki.slice(0, -1);
  }
</script>

<div id="form">
  <div class="form-control">
    <label for="ime">Ime</label>
    <input type="text" bind:value={ime} id="ime" />
  </div>
  <div class="form-control">
    <label for="poklic">Poklic</label>
    <input type="text" bind:value={poklic} id="poklic" />
  </div>
  <div class="form-control">
    <label for="opis">Opis</label>
    <textarea rows="3" bind:value={opis} id="opis" />
  </div>
</div>

<button on:click={dodajStik}>Dodaj stik</button>
<button on:click="{izbrisiPrvega}">Izbriši prgvega</button>
<button on:click="{izbrisiZadnjega}">Izbriši zadnjega</button>

{#if formState === "invalid"}
  <p>Prosimo, da pravilno izpolnite vsa polja.</p>
{:else}
  <p>Prosimo, vnesite podatke.</p>
{/if}

{#each stiki as stik, i (stik.id)}
  <h2>#{i + 1}</h2>
  <Stik ime={stik.ime} poklic={stik.poklic} opis={stik.opis} />
{/each}

<style>
  #form {
    width: 30rem;
    max-width: 100%;
  }
</style>
