package com.ainsigne.wesoarhightodo_ainsigne.utils

import android.os.Build
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.Task
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList


/**
 * Constants used throughout the app.
 */
const val DATABASE_NAME = "todo_db"
const val FAKE_BUILD = "fake"
const val BASE_API = "https://my-json-server.typicode.com/wsh-startup/mock-api/"

const val taskResponse : String = "[\n" +
        "    {\n" +
        "        \"id\": 1,\n" +
        "        \"title\": \"Methyl Salicylate Menthol Eucalyptus\",\n" +
        "        \"date\": \"07/11/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 2,\n" +
        "        \"title\": \"Agnus castus, Ambra grisea, Conium maculatum, Apiolum, Estradiol, Estriol, Folliculinum, Ldopa, Melatonin, Testosterone, Alpha-lipoicum acidum,\",\n" +
        "        \"date\": \"09/11/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 3,\n" +
        "        \"title\": \"Homosalate, Octisalate, Avobenzone, Octocrylene, Oxybenzone\",\n" +
        "        \"date\": \"09/21/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 4,\n" +
        "        \"title\": \"Valsartan and Hydrochlorothiazide\",\n" +
        "        \"date\": \"12/22/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 5,\n" +
        "        \"title\": \"Ethyl Alcohol\",\n" +
        "        \"date\": \"11/11/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 6,\n" +
        "        \"title\": \"Acetaminophen, Dextromethorphan Hydrobromide, Phenylephrine Hydrochloride\",\n" +
        "        \"date\": \"12/26/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 7,\n" +
        "        \"title\": \"Poverty Weed\",\n" +
        "        \"date\": \"12/06/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 8,\n" +
        "        \"title\": \"Aluminum Chlorohydrate\",\n" +
        "        \"date\": \"12/29/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 9,\n" +
        "        \"title\": \"Metronidazole\",\n" +
        "        \"date\": \"11/24/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 10,\n" +
        "        \"title\": \"Levocetirizine Dihydrochloride\",\n" +
        "        \"date\": \"11/06/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 11,\n" +
        "        \"title\": \"CLOTRIMAZOLE\",\n" +
        "        \"date\": \"08/06/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 12,\n" +
        "        \"title\": \"methylergonovine maleate\",\n" +
        "        \"date\": \"10/11/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 13,\n" +
        "        \"title\": \"Naproxen Sodium\",\n" +
        "        \"date\": \"11/28/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 14,\n" +
        "        \"title\": \"Duloxetine\",\n" +
        "        \"date\": \"09/21/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 15,\n" +
        "        \"title\": \"BENZETHONIUM CHLORIDE\",\n" +
        "        \"date\": \"07/30/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 16,\n" +
        "        \"title\": \"TRICLOSAN\",\n" +
        "        \"date\": \"08/24/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 17,\n" +
        "        \"title\": \"Hydrogen Peroxide\",\n" +
        "        \"date\": \"11/26/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 18,\n" +
        "        \"title\": \"vardenafil hydrochloride\",\n" +
        "        \"date\": \"10/19/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 19,\n" +
        "        \"title\": \"Avobenzone, Homosalate, Octisalate, Octocrylene, and Oxybenzone\",\n" +
        "        \"date\": \"11/16/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 20,\n" +
        "        \"title\": \"romiplostim\",\n" +
        "        \"date\": \"11/27/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 21,\n" +
        "        \"title\": \"lidocaine\",\n" +
        "        \"date\": \"07/12/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 22,\n" +
        "        \"title\": \"Leonurus cardiaca, Agaricus muscarius, Arsenicum album, Cocculus indicus, Conium maculatum, Petroleum, Phosphorus, Pulsatilla, Tabacum, Theridion,\",\n" +
        "        \"date\": \"10/20/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 23,\n" +
        "        \"title\": \"Aspirin\",\n" +
        "        \"date\": \"10/10/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 24,\n" +
        "        \"title\": \"Acetaminophen, Diphenhydramine HCL, and Phenylephrine HCL.\",\n" +
        "        \"date\": \"12/18/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 25,\n" +
        "        \"title\": \"Ethyl Alcohol\",\n" +
        "        \"date\": \"11/26/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 26,\n" +
        "        \"title\": \"Valsartan and hydrochlorothiazide\",\n" +
        "        \"date\": \"12/02/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 27,\n" +
        "        \"title\": \"Oxcarbazepine\",\n" +
        "        \"date\": \"12/16/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 28,\n" +
        "        \"title\": \"Ibuprofen\",\n" +
        "        \"date\": \"09/18/2020\",\n" +
        "        \"completed\": false\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 29,\n" +
        "        \"title\": \"Oxycodone hydrochloride and Acetaminophen\",\n" +
        "        \"date\": \"10/23/2020\",\n" +
        "        \"completed\": true\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": 30,\n" +
        "        \"title\": \"Miconazole nitrate\",\n" +
        "        \"date\": \"07/19/2020\",\n" +
        "        \"completed\": false\n" +
        "    }\n" +
        "]"
val fakeTasks : List<Task> = Gson().fromJson(taskResponse, object : TypeToken<ArrayList<Task>?>() {}.type)

