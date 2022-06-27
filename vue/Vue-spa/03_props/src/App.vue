<template>
  <section>
    <header><h1>Moji stiki</h1></header>
    <ul>
      <uporabniski-stiki
        v-for="stik in stiki"
        v-bind:key="stik.id"
        v-bind:ime-stika="stik.ime"
        v-bind:tel-stika="stik.tel"
        v-bind:email-stika="stik.email"
        v-bind:je-priljubljen="true"
      ></uporabniski-stiki>
    </ul>
  </section>
</template>

<script>
export default {
  data() {
    return {
      stiki: [
        {
          id: "jovan",
          ime: "Jovan Novakovič",
          tel: "0038676543210",
          email: "novakovic@gmail.com",
        },
        {
          id: "janez",
          ime: "Janez Novak",
          tel: "0038601234567",
          email: "novak@gmail.com",
        },
      ],
    };
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Jost&display=swap");
* {
  box-sizing: border-box;
}

html {
  font-family: "Jost", sans-serif;
}

body {
  margin: 0;
}

header {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  margin: 3rem auto;
  border-radius: 10px;
  padding: 1rem;
  background-color: #58004d;
  color: white;
  text-align: center;
  width: 90%;
  max-width: 40rem;
}

#app ul {
  margin: 0;
  padding: 0;
  list-style: none;
}

#app li {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  margin: 1rem auto;
  border-radius: 10px;
  padding: 1rem;
  text-align: center;
  width: 90%;
  max-width: 40rem;
}

#app h2 {
  font-size: 2rem;
  border-bottom: 4px solid #ccc;
  color: #58004d;
  margin: 0 0 1rem 0;
}

#app button {
  font: inherit;
  cursor: pointer;
  border: 1px solid #ff0077;
  background-color: #ff0077;
  color: white;
  padding: 0.05rem 1rem;
  box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.26);
}

#app button:hover,
#app button:active {
  background-color: #ec3169;
  border-color: #ec3169;
  box-shadow: 1px 1px 4px rgba(0, 0, 0, 0.26);
}
</style>