<template>
  <section>
    <base-card>
      <h2>Submitted Experiences</h2>
      <div>
        <base-button @click="loadExperiences"
          >Load Submitted Experiences</base-button
        >
      </div>
      <p v-if="isLoading">Loading...</p>
      <ul v-if="!isLoading">
        <survey-result
          v-for="result in results"
          :key="result.id"
          :name="result.name"
          :rating="result.rating"
        ></survey-result>
      </ul>
    </base-card>
  </section>
</template>

<script>
import SurveyResult from './SurveyResult.vue';

export default {
  components: {
    SurveyResult,
  },
  data() {
    return {
      results: [],
      isLoading: false
    };
  },
  methods: {
    loadExperiences() {
      this.isLoading = true;
      fetch('https://vue-http-34b34-default-rtdb.firebaseio.com/surveys.json', {
        method: 'GET'
      }).then(responce => {
        return responce.json();
      }).then(data => {
        this.isLoading = false;
        const results = [];      
        for (const id in data) {
          results.push({id: id, name: data[id].name, rating: data[id].rating});
        }

        this.results = results;
      });
    },
  },
  mounted() {
    this.loadExperiences();
  }
};
</script>

<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
</style>