<script>
  import Izdelek from "./Izdelek.svelte";
</script>

<Izdelek
  imeIzdelka="Kruh"
  on:dodaj-izdelek={() => alert("Kliked")}
  on:delete={() => alert("Delete")}
/>
