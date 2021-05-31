<template>
  <div>
    <table class="table table-sm">
      <thead>
        <tr>
          <th>Origem</th>
          <th>Destino</th>
          <th>Valor</th>
          <th>Taxa</th>
          <th>Data</th>
          <th>Agendamento</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in list" :key="item.id">
          <td v-text="item.origin.name"></td>
          <td v-text="item.destiny.name"></td>
          <td>{{ item.value | money }}</td>
          <td> {{ item.tax | money }}</td>
          <td v-text="$date(item.date).format('DD/MM/YYYY')"></td>
          <td v-text="$date(item.scheduling).format('DD/MM/YYYY')"></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  layout: "dash",
  data() {
    return {
      list: [],
    };
  },
  mounted() {
    this.$axios.get("/api/transfers/my").then((r) => (this.list = r.data));
  },
  filters: {
    money: function (value) {
      return new Number(value).toLocaleString("pt-br", {
        minimumFractionDigits: 2,
      });
    },
  },
};
</script>

<style>
</style>