<script>
  import { onMount } from "svelte";

  let hobbies = [];
  let hobbyInput;
  let isLoading = false;

  onMount(() => {
    isLoading = true;

    fetch("https://svelte-http-83b2d-default-rtdb.firebaseio.com/hobbies.json")
      .then((res) => {
        if (!res.ok) {
          isLoading = false;
          throw new Error("Failed");
        }
        return res.json();
      })
      .then((data) => {
        isLoading = false;
        const results = [];
        for (const id in data) {
          results.push(data[id]);
        }

        hobbies = [...results];
      });
  });

  function addHobbies() {
    isLoading = true;
    fetch(
      "https://svelte-http-83b2d-default-rtdb.firebaseio.com/hobbies.json",
      {
        method: "POST",
        body: JSON.stringify(hobbyInput.value),
        headers: { "Content-Type": "aplication/json" },
      }
    )
      .then((res) => {
        isLoading = false;
        if (!res.ok) {
          throw new Error("Failed");
        }
        alert("Saved data.");
      })
      .catch((err) => {
        isLoading = false;
        console.log(err);
      });
  }
</script>

<label for="hobby">Hobby</label>
<input type="text" id="hobby" bind:this={hobbyInput} />
<button on:click={addHobbies}>Add hobby</button>

{#if isLoading}
  <p>Loading...</p>
{:else}
  <ul>
    {#each hobbies as hobby}
      <li>{hobby}</li>
    {/each}
  </ul>
{/if}
