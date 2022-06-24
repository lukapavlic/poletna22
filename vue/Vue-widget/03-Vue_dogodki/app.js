const app = Vue.createApp({
  data() {
    return {
      stevec: 0,
      ime: ''
    };
  },
  methods: {
    dodaj: function (stevilo) {
      this.stevec = this.stevec + stevilo;
    },
    odstrani: function (stevilo) {
      this.stevec = this.stevec - stevilo;
    },
    setIme: function (event) {
      this.ime = event.target.value;
    },
    ponastavi: function () {
      this.ime = '';
    }
  }
});

app.mount('#dogodki');
