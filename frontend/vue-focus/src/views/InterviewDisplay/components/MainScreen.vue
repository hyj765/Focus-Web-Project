<template>
  <div class="main">
    <div id="main-video">
      <user-video :stream-manager="streamManager" :main-stream="true" />
    </div>
  </div>
</template>

<script>
import UserVideo from './UserVideo.vue';

export default {
  components: {
    UserVideo,
  },

  props: {
    // eslint-disable-next-line
    streamManager: Object,
    isSpeak: Boolean,
    mainStream: Boolean,
  },
};
</script>
<style scoped>
.main {
  height: 70vh;
  width: 100%;
  border-radius: 0.7rem;
}
</style>
