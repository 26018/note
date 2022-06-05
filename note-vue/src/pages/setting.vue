<template>
    <div id="setting">
        <div class="poemKfram" v-if="notes != null" v-for="note in notes">
            <PoemEditCard :note="note" />
        </div>
    </div>
</template>

<script>
import { notes } from '../common/note';
import PoemEditCard from '../components/poemEditCard.vue';
export default {
    name: 'setting',
    data() {
        return {
            notes: [],
        };
    },
    components: { PoemEditCard },

    created() {
        notes().then((res) => {
            this.notes = res.data;
        });
    },
};
</script>

<style>
.el-message,
.el-message-box {
    max-width: 90% !important;
}
</style>
<style lang="css" scoped>
#setting {
    width: 100%;
    height: 90%;
}

.poemKfram {
    width: 90%;
    margin: 0 auto;
}
</style>
