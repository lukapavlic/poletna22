<script>
    import { createEventDispatcher } from "svelte";

    export let imeIzdelka;

    const dispatch = createEventDispatcher();

    function dodaj() {
        dispatch("dodaj-izdelek", { id: "iz1" });
    }
</script>

<article>
    <h1>{imeIzdelka}</h1>
    <button on:click={dodaj}>Dodaj</button>
    <button
        on:click={() => {
            dispatch("delete", "iz1");
        }}>Odstrani</button
    >
</article>
