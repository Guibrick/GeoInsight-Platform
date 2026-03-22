<template>
  <div class="container mt-4">
    <header class="mb-4 text-center">
      <h1>GeoInsight Platform</h1>
    </header>

    <div class="row">
      <div class="col-lg-4 mb-4 d-flex flex-column">
        <div class="card mb-3 p-3 shadow-sm">
          <h4 class="card-title">Add Station</h4>
          <StationForm />
        </div>

        <div class="card p-3 shadow-sm" style="height: 500px;">
          <StationList />
        </div>
      </div>

      <div class="col-lg-8">
        <div class="card shadow-sm p-3">
          <h4 class="card-title mb-3">Map</h4>
          <div style="height: 700px;">
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