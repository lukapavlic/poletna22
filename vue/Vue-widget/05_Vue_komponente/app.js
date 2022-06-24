const app = Vue.createApp({
    data() {
        return {
            stiki: [
                { id: 'jovan', ime: 'Jovan Novakovič', tel: '00386654321', email: 'novakovic@gmail.com' }
            ]
        }
    }
});
app.component('uporabniski-stiki', {
    template: `
    <li>
        <h2>{{ stik.ime }}</h2>
        <button v-on:click="toggle">{{ podakiVidni ? 'Skrij' : 'Prikaži' }} podrobnosti</button>
        <ul v-if="podakiVidni">
          <li><strong>Tel:</strong> {{ stik.tel }}</li>
          <li><strong>Email:</strong> {{ stik.email }}</li>
        </ul>
    </li>
    `,
    data() {
        return {
            podakiVidni: false,
            stik: {
                id: 'janez', ime: 'Janez Novak', tel: '00386123456', email: 'novak@gmail.com'
            }
        };
    },
    methods: {
        toggle() {
            this.podakiVidni = !this.podakiVidni;
        }
    }
})
app.mount('#app');