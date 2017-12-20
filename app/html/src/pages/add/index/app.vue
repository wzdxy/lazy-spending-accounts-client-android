<template>
    <div id="app">
        <v-app>
            <!--<v-container fluid="fluid" class="text-xs-center" style="padding: 0;">-->
                <v-layout row wrap class="text-xs-center">
                    <v-flex xs12>
                        <v-card height="300px" flat color="white">
                            <v-card-text>
                                <img v-bind:src="logoImg"/>

                                <div>
                                    <v-btn color="primary" @click.stop="dialog = true">Open Dialog 1</v-btn>
                                </div>
                                <div>
                                    <v-btn color="error">Error</v-btn>
                                </div>
                                <div>
                                    <v-btn light disabled>Disabled</v-btn>
                                </div>
                            </v-card-text>
                        </v-card>
                    </v-flex>
                </v-layout>

            <!--</v-container>-->
            <v-dialog v-model="dialog">
                <v-layout row wrap>
                    <v-flex xs12>
                        <v-card flat >
                            <v-card-text>
                                <div>
                                    <v-btn color="primary">Primary</v-btn>
                                </div>
                                <div>
                                    <v-btn color="error">Error</v-btn>
                                </div>
                                <div>
                                    <v-btn color="primary" disabled>Disabled</v-btn>
                                </div>
                            </v-card-text>
                            <v-card-actions>
                                <v-btn color="primary" flat @click.stop="dialog=false">Close</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-flex>
                </v-layout>

            </v-dialog>
        </v-app>
    </div>

</template>

<script>
    import logo from 'assets/img/logo.png'
    import modal from 'components/modal.vue'

    export default {
        components: {
            modal
        },
        data() {
            return {
                msg: 'Use Vue 2.0 Today!',
                logoImg: logo,
                dialog: false
            }
        },
        methods: {
            gogogo() {
                location.assign('../customer/home.html')
            },
            tototo() {
                location.assign('../user/login.html')
            }
        }
    }
</script>

<style lang="postcss">
    .index-card {
        width: 800px;
        margin: 100px auto;
    }

    img {
        height: 100px;
    }
</style>
