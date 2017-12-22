<template>
    <div id="app">
        <v-app>
            <v-layout row wrap class="text-xs-center">
                <v-flex xs12>
                    <v-card height="100%" flat color="white">
                        <v-card-text row wrap>


                            <v-flex xs12>
                                <v-dialog persistent width="290px" id="data-dialog">
                                    <v-text-field
                                            slot="activator"
                                            label="Date"
                                            v-model="costDate"
                                            prepend-icon="event"
                                            readonly
                                    ></v-text-field>
                                    <v-date-picker v-model="costDate" actions>
                                        <template slot-scope="{ save, cancel }">
                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn flat color="primary" @click="cancel">Cancel</v-btn>
                                                <v-btn flat color="primary" @click="save">OK</v-btn>
                                            </v-card-actions>
                                        </template>
                                    </v-date-picker>
                                </v-dialog><v-dialog persistent width="290px" id="time-dialog">
                                    <v-text-field
                                            slot="activator"
                                            label="Time"
                                            placeholder="Let me go"
                                            v-model="costTime"
                                            prepend-icon="access_time"
                                            readonly
                                    ></v-text-field>
                                    <v-time-picker v-model="costTime" actions format="24hr">
                                        <template slot-scope="{ save, cancel }">
                                            <v-card-actions>
                                                <v-btn flat color="primary" @click="cancel">Cancel</v-btn>
                                                <v-btn flat color="primary" @click="save">Save</v-btn>
                                            </v-card-actions>
                                        </template>
                                    </v-time-picker>
                                </v-dialog>
                            </v-flex>
                            <v-select
                                    xs12
                                    label="Serial"
                                    v-bind:items="allSerial"
                                    v-model="serial"
                                    max-height="300"
                            ></v-select>
                            <v-text-field
                                    label="Cost"
                                    type="number"
                                    v-model="cost"
                            ></v-text-field>
                        </v-card-text>
                        <v-card-actions style="flex-direction: row-reverse;">
                            <v-btn primary  @click="saveCost">Save</v-btn>
                            <v-btn flat  @click="resetForm">Reset</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-app>
    </div>

</template>

<script>

    export default {
        data() {
            return {
                allSerial: ['Alabama', 'Alaska', 'American Samoa', 'Arizona', 'Oklahoma', 'Oregon', 'Palau', 'Pennsylvania', 'Puerto Rico'],
                serial: [],
                cost: '',
                costDate: '',
                costTime: ''
            }
        },
        methods: {
            saveCost() {
                if(this.cost==='')return 0;
                if(window.native)native.addAccount(JSON.stringify({
                    cost:this.cost,
                    costDate:this.costDate,
                    costTime:this.costTime,
                    serial:this.serial
                }))
            },
            resetForm() {
                let today=new Date()
                this.costDate=today.getFullYear()+'-'+(today.getMonth()+1+'-').padLeft(3,'0')+String(today.getDate()).padLeft(2,'0')
                this.time=''
                this.cost=''
                this.serial=[]
            }
        },
        created(){
            this.resetForm();
        }
    }
</script>

<style lang="postcss">
    .input-group {
        /*padding-top:6px;*/
    }

    .picker__title {
        display: none;
    }

    .card__text>div{
        height: 56px;
    }

    .picker--time > .picker__title {
        display: none;
    }

    #data-dialog, #time-dialog {
        width: 50%;
    }
</style>
