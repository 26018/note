<template>
    <div id="read" v-if="notes != null">
        <div class="countDiv">「{{ notes.length }}」</div>
        <div class="poemDiv" v-for="note in notes">
            <PoemCard :note="note" />
        </div>
    </div>
</template>

<script>
import { notes } from '../common/note';
import PoemCard from '../components/poemCard.vue';

export default {
    name: 'read',
    data() {
        return {
            notes: [],
        };
    },
    components: { PoemCard },

    created() {
        notes().then((res) => {
            this.notes = JSON.parse(res.data);
        });
    },
};
</script>
<style lang="css" scoped>
#read {
    height: 90%;
    width: 100%;
}

.poemDiv,
.countDiv {
    width: 90%;
    margin: 0 auto;
}

.countDiv {
    color: gray;
}
</style>

<style>
.el-message,
.el-message-box {
    max-width: 90% !important;
}
</style>
