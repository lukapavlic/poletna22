const app = Vue.createApp({
  data() {
    return {
      novCilj: '',
      cilji: []
    };
  },
  methods: {
    dodajCilj: function () {
      this.cilji.push(this.novCilj);
    },
    odstraniCilj: function(index) {
      this.cilji.splice(index, 1);
    }
  }
});

app.mount('#opravila');
