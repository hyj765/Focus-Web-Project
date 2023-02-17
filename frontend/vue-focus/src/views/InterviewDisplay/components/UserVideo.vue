<template>
  <div v-if="streamManager">
    <div>
      <ov-video
        :stream-manager="streamManager"
        :is-speak="isSpeak"
        :main-stream="mainStream"
      />

      <p>
        {{ clientData }}
      </p>
    </div>
  </div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
  name: 'UserVideo',

  components: {
    OvVideo,
  },

  props: {
    // eslint-disable-next-line
    streamManager: Object,
    mainStream: Boolean,
    isSpeak: Boolean,
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>

<style scoped>
#UserVideo {
  /* display: inline-block; */
  margin: 0;
  /* width: auto; */
  /* height: auto; */
}
.user-name {
  margin-bottom: 0;
  font-size: 1.8vh;
}
</style>
