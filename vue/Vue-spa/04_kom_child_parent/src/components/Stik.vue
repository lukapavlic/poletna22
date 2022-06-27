<template>
  <li>
    <h2>{{ imeStika }} {{ jePriljubljen ? '(Priljubljen)' : ''}}</h2>
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
    id: {
      type: String,
      required: true
    },
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
  //emmits: ['toggle-je-priljubljen'],
  emits: {
    'toggle-je-priljubljen': function(id) {
      if (id) {
        return true;
      } else {
        console.warn('Manjka id!');
        return false;
      }
    }
  },
  data() {
    return {
      jePrikazano: false
    };
  },
  methods: {
    toggle() {
      this.jePrikazano = !this.jePrikazano;
    },
    togglePriljubljen() {
      this.$emit('toggle-je-priljubljen', this.id);
    }
  },
};
</script>