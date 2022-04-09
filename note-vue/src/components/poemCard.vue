<template>
    <div class="some">
        <h3>
            <li>{{ poem.title }}</li>
        </h3>
        <blockquote class="poem" v-html="utils.noteParse(poem.content, poem.time)" />
    </div>
</template>

<script>
export default {
    name: 'poem',
    props: {
        poem: {
            title: String,
            content: String,
            time: String,
        },
    },
    data() {
        return {};
    },
    methods: {},
};
</script>

<style scoped>
* {
    padding: 0;
    margin: 0;
}
.some {
    margin: 0 auto;
    width: 100%;
    margin: 28px auto;
}
h3 {
    text-align: left;
}

blockquote {
    border-radius: 10px;
    font-size: 14px;
    width: 100%;
    color: grey;
}
.poem >>> p {
    text-align: left;
    margin: 14px;
}
</style>
