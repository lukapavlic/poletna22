const app = Vue.createApp({
    data: function() {
        return {
            cilj1: 'Preuči primere',
            cilj2: 'Opravi nalogo',
            povezavaVec: 'https://vuejs.org'
        };
    },
    methods: {
        izpisCilja: function() {
            const random = Math.random();
            if (random < 0.5) {
                return this.cilj1;
            } else {
                return this.cilj2;
            }
        }
    }
});

app.mount('#cilji');