<template>
  <form @submit.prevent="submit">
    <div class="form-control">
      <label for="user-name">Ime in priimek</label>
      <input id="user-name" name="user-name" type="text" v-model="ime" />
    </div>
    <div class="form-control">
      <label for="age">Starost</label>
      <input id="age" name="age" type="number" v-model="starost" />
    </div>
    <div class="form-control">
      <label for="city">Kraj rojstva</label>
      <select id="city" name="city" v-model="city">
        <option value="maribor">Maribor</option>
        <option value="ljubljana">Ljubljana</option>
        <option value="celje">Celje</option>
      </select>
    </div>
    <div class="form-control">
      <h2>Katere tehnologije poznate?</h2>
      <div>
        <input id="tech-react" name="tech" type="checkbox" value="react" v-model="tech" />
        <label for="tech-react">React</label>
      </div>
      <div>
        <input id="tech-vue" name="tech" type="checkbox" value="vue" v-model="tech" />
        <label for="tech-vue">Vue</label>
      </div>
      <div>
        <input id="tech-svelte" name="tech" type="checkbox" value="svelte" v-model="tech" />
        <label for="tech-svelte">Svelte</label>
      </div>
    </div>
    <div class="form-control">
      <h2>Kakšen način učenja preferirate?</h2>
      <div>
        <input id="how-video" name="how" type="radio" value="video" v-model="how" />
        <label for="how-video">Video posnetki</label>
      </div>
      <div>
        <input id="how-slides" name="how" type="radio" value="slides" v-model="how" />
        <label for="how-slides">Slajde</label>
      </div>
      <div>
        <input id="how-examples" name="how" type="radio" value="examples" v-model="how" />
        <label for="how-examples">Primeri</label>
      </div>
    </div>
    <div>
      <button>Pošlji</button>
    </div>
  </form>
</template>

<script>
export default {
  data() {
    return {
      ime: '',
      starost: null,
      city: 'ljubljana',
      tech: [],
      how: null,
    };
  },
  methods: {
    submit() {
      console.log(this.ime);
      this.ime = '';
      console.log(this.starost);
      this.starost = null;
      console.log(this.city);
      this.city = 'ljubljana';
      console.log(this.tech);
      this.tech = [];
      console.log(this.how);
      this.how = null;
    },
  },
};
</script>

<style scoped>
form {
  margin: 2rem auto;
  max-width: 40rem;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  padding: 2rem;
  background-color: #ffffff;
}

.form-control {
  margin: 0.5rem 0;
}

label {
  font-weight: bold;
}

h2 {
  font-size: 1rem;
  margin: 0.5rem 0;
}

input,
select {
  display: block;
  width: 100%;
  font: inherit;
  margin-top: 0.5rem;
}

select {
  width: auto;
}

input[type='checkbox'],
input[type='radio'] {
  display: inline-block;
  width: auto;
  margin-right: 1rem;
}

input[type='checkbox'] + label,
input[type='radio'] + label {
  font-weight: normal;
}

button {
  font: inherit;
  border: 1px solid #0076bb;
  background-color: #0076bb;
  color: white;
  cursor: pointer;
  padding: 0.75rem 2rem;
  border-radius: 30px;
}

button:hover,
button:active {
  border-color: #002350;
  background-color: #002350;
}
</style>