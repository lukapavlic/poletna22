// const buttonEl = document.querySelector('button');
// const inputEl = document.querySelector('input');
// const listEl = document.querySelector('ul');

// function addGoal() {
//     const enteredValue = inputEl.value;
//     const listItemEl = document.createElement('li');
//     listItemEl.textContent = enteredValue;
//     listEl.appendChild(listItemEl);

//     inputEl.value = '';
// }

// buttonEl.addEventListener('click', addGoal);

Vue.createApp({
    data: function() {
        return {
            opravila: [],
            enteredValue: ''
        };
    },
    methods: {
        dodajOpravilo() {
            this.opravila.push(this.enteredValue);
        }
    }
}).mount('#app');