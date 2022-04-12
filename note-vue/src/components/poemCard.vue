<template>
    <div class="some">
        <h3>
            <li>{{ note.title }}</li>
        </h3>
        <blockquote class="poem" v-html="parse(note.content, note.time)" />
    </div>
</template>

<script>
import { noteParse } from '../common/note';
export default {
    name: 'note',
    props: {
        note: {
            title: String,
            content: String,
            time: String,
        },
    },
    data() {
        return {};
    },
    methods: {
        parse(content, time) {
            return noteParse(content, time);
        },
    },
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
