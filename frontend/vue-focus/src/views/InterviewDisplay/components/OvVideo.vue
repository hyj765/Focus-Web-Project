<template>
  <video v-if="mainStream" class="main" autoplay></video>
  <video
    v-else
    class="sub"
    :class="{ 'video-isSpeak': isSpeak }"
    autoplay
  ></video>
</template>

<script>
export default {
  name: 'OvVideo',

  props: {
    // eslint-disable-next-line
    streamManager: Object,
    mainStream: Boolean,
    isSpeak: Boolean,
  },
  watch: {
    streamManager: 'changeDetect',
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },

  updated() {
    this.streamManager.addVideoElement(this.$el);
  },

  methods: {
    changeDetect() {
      this.streamManager.addVideoElement(this.$el);
    },
  },
};
</script>

<style scoped>
.main {
  /* height: 70vh; */
  width: 100%;
  border-radius: 0.7rem;
}
.sub {
  width: 29vh;
  height: 18vh;
  overflow: hidden;
  object-fit: cover;
  border-radius: 1rem;
}
.video-isSpeak {
  /* border: solid 2.5px rgb(0, 242, 250); */
  border: solid 3px rgb(255, 0, 0);
  border-radius: 1rem;
}

#video {
  width: 100%;
  height: auto;
  widows: auto;
}
</style>
