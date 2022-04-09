<template>
    <div id="setting">
        <div class="poemKfram" v-if="poems != undefined || poems != null" v-for="poem in poems">
            <PoemEditCard :poem="poem" />
        </div>
    </div>
</template>

<script>
import PoemEditCard from '../components/poemEditCard.vue';
export default {
    name: 'setting',
    data() {
        return {
            poems: [],
        };
    },
    components: { PoemEditCard },

    created() {
        let that = this;
        this.utils.notes(function(data) {
            that.poems = data;
        });
    },
};
</script>
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
