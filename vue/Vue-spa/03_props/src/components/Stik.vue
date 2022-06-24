<template>
  <li>
    <h2>{{ imeStika }} {{ stikJePriljubljen ? '(Priljubljen)' : ''}}</h2>
    <button @click="togglePriljubljen">
      Dodaj/Odstrani priljubljen
    </button>
    <button @click="toggle">
      {{ jePrikazano ? "Skrij" : "Prikaži" }} podrobnosti
    </button>
    <ul v-if="jePrikazano">
      <li><strong>Tel.</strong> {{ telStika }}</li>
      <li><strong>Email</strong> {{ emailStika }}</li>
    </ul>
  </li>
</template>

<script>
export default {
  props: {
    imeStika: {
      type: String,
      required: true
    },
    telStika: {
      type: String,
      required: true
    },
    emailStika: {
      type: String,
      required: true,
      validator: function(value) {
        return value.includes('@');
      }
    },
    jePriljubljen: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data() {
    return {
      jePrikazano: false,
      stikJePriljubljen: this.jePriljubljen
    };
  },
  methods: {
    toggle() {
      this.jePrikazano = !this.jePrikazano;
    },
    togglePriljubljen() {
      this.stikJePriljubljen = !this.stikJePriljubljen;
    }
  },
};
</script>