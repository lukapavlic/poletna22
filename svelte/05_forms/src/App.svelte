<script>
  import CustomInput from "./CustomInput.svelte";
  let val = "Viktor";
  let agreed;
  let favCourse = ["javascript"];
  let favColor = "green";
  let singleColor = 'red';

  $: console.log(agreed);
  $: console.log(favCourse);
  $: console.log(favColor);
  $: console.log(singleColor);

  function setValue(event) {
    val = event.target.value;
  }
</script>

<!-- <input type="text" value={val} on:input={setValue} /> -->
<input type="text" bind:value={val} />
<CustomInput bind:val />

<label for="">
  <input type="checkbox" bind:checked={agreed} />
  Agree to terms
</label>

<h1>Favorite course?</h1>
<label for="">
  <input
    type="checkbox"
    value="javascript"
    name="course"
    bind:group={favCourse}
  />
  JavaScript
</label>
<label for="">
  <input type="checkbox" value="react" name="course" bind:group={favCourse} />
  React
</label>
<label for="">
  <input type="checkbox" value="svelte" name="course" bind:group={favCourse} />
  Svelte
</label>

<h1>Favorite color?</h1>
<label for="">
  <input type="radio" value="red" name="color" bind:group={favColor} />
  Red
</label>
<label for="">
  <input type="radio" value="green" name="color" bind:group={favColor} />
  Green
</label>
<label for="">
  <input type="radio" value="blue" name="color" bind:group={favColor} />
  Blue
</label>

<select bind:value={singleColor}>
  <option value="green">Green</option>
  <option value="red">Red</option>
  <option value="blue">Blue</option>
</select>
