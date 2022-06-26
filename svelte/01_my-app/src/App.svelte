<script>
  let ime = "Janez";
  let priimek = "Novak";
  let starost = 33;

  function povecajStarost() {
    starost += 1;
  }

  function setIme(event) {
    ime = event.target.value;
  }
</script>

<h1>{ime} {priimek} {starost}</h1>
<button on:click={povecajStarost}>Povečaj starost</button>

<!-- <input type="text" value={ime} on:input={setIme} /> -->
<input type="text" bind:value={ime} />

<style>
  h1 {
    color: purple;
  }
</style>
