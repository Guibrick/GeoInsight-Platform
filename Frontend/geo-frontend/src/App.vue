<template>
  <div class="container mt-4">
    <header class="mb-4 text-center">
      <h1 class="hero-title">Pollution Track Platform</h1>
    </header>

    <div class="row">
      <div class="col-lg-4 mb-4 d-flex flex-column">
        <div class="card mb-3 p-3 shadow-sm">
          <h3 class="card-title">Add Pollution Report</h3>
          <StationForm />
        </div>

        <div class="card p-3 shadow-sm" style="height: 500px;">
          <h3 class="mb-3">Pollution Control Stations</h3>
          <StationList />
        </div>
      </div>

      <div class="col-lg-8">
        <div class="card shadow-sm p-3">
          <h3 class="card-title mb-3">Map</h3>
          <div style="height: 750px;">
            <StationMap />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import StationForm from './components/StationForm.vue'
import StationList from './components/StationList.vue'
import StationMap from './components/StationMap.vue'
</script>

<style>
.hero-title {
  font-size: 3rem;
  font-weight: 800;
  background: linear-gradient(90deg, #9aa78d, #84ea8e);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  letter-spacing: -1px;
  text-shadow: 1px 1px 4px rgba(0,0,0,0.2);
  margin: 0 0 2rem;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  transition: transform 0.2s ease;
}

.hero-title:hover {
  transform: scale(1.05);
}
</style>