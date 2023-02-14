<template>
  <div v-if="streamManager">
    <div class="list">
      <div v-if="streamManager.stream.connection.role == 'PUBLISHER'">
        <p class="publisher_id">{{ clientData }} (나)</p>
        <hr />
      </div>
      <div v-else>
        <p>{{ clientData }}</p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'UserList',

  props: {
    // eslint-disable-next-line
    streamManager: Object,
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
