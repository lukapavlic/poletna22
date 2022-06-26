<script>
  import Stik from './Stik.svelte';

  let ime = "Janez Novak";
  let poklic = '';
  let opis = '';

</script>

<h1>{ime}</h1>
<button>Povečaj starost</button>

<!-- <input type="text" value={ime} on:input={setIme} /> -->
<input type="text" bind:value={ime} />
<input type="text" bind:value={poklic} />
<input type="text" bind:value={opis} />

<Stik ime={ime} poklic={poklic} opis={opis}/>

<style>
  h1 {
    color: purple;
  }
</style>
