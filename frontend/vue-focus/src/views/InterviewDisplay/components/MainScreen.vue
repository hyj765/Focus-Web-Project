<template>
  <div>
    <user-video :stream-manager="streamManager" :main-stream="true" />
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
/* .main {
  height: 70vh;
  width: 120%;
  border-radius: 0.7rem;
} */
</style>
