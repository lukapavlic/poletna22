<template>
  <li>
    <h2>{{ stik.ime }}</h2>
    <button @click="toggle">{{ jePrikazano ? 'Skrij' : 'Prikaži'}} podrobnosti</button>
    <ul v-if="jePrikazano">
      <li><strong>Tel.</strong> {{ stik.tel }}</li>
      <li><strong>Email</strong> {{ stik.email }}</li>
    </ul>
  </li>
</template>

<script>
export default {
  data() {
    return {
      jePrikazano: false,
      stik: {
        id: "janez",
        ime: "Janez Novak",
        tel: "0038601234567",
        email: "novak@gmail.com",
      },
    };
  },
  methods: {
    toggle() {
      this.jePrikazano = !this.jePrikazano;
    },
  },
};
</script>