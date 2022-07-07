package gui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.ConfigData
import model.create_genome
import model.persistent_genome

@Composable
fun componentConfigScreen(screenState:MutableState<Int>){
    var configID: MutableState<Int> = rememberSaveable() {
        mutableStateOf(0)
    }
    if (configID.value == 0){
        config0(configID)
    }else if (configID.value == 1){
        commonScreen(configID)
    }else if(configID.value == 2){
        create_genomeScreen(configID)
    }else if (configID.value ==3){
        evaluatorScreen(configID)
    }else if (configID.value == 4){
        adaptive_evo_ops_selectorScreen(configID)
    }else if (configID.value == 5){
        replace_dead_genomeScreen(configID)
    }else if (configID.value == 6){
        replace_similar_genomeScreen(configID)
    }else if (configID.value == 7){
        delta_mutatorScreen(configID)
    }else if (configID.value == 9){
        random_mutatorScreen(configID)
    }else if (configID.value == 11){
        crossoverScreen(configID)
    }else if (configID.value == 13){
        screenState.value=0
    }
}

@Composable
fun config0(configID:MutableState<Int>){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Select Config",
            fontSize = 20.sp,
            color = Color.White
        )
        Column(
            horizontalAlignment = Alignment.Start
        ){
            Button(onClick = {configID.value=1}, modifier = Modifier.width(250.dp)){
                Text("common")
            }
            Button(onClick = {configID.value=2}, modifier = Modifier.width(250.dp)){
                Text("create genome")
            }
            Button(onClick = {configID.value=3}, modifier = Modifier.width(250.dp)){
                Text("evaluator")
            }
            Button(onClick = {configID.value=4}, modifier = Modifier.width(250.dp)){
                Text("adaptive evo ops selector")
            }
            Button(onClick = {configID.value=5}, modifier = Modifier.width(250.dp)){
                Text("replace dead genome")
            }
            Button(onClick = {configID.value=6}, modifier = Modifier.width(250.dp)){
                Text("replace similar genome")
            }
            Button(onClick = {configID.value=7}, modifier = Modifier.width(250.dp)){
                Text("delta mutator")
            }
            Button(onClick = {configID.value=9}, modifier = Modifier.width(250.dp)){
                Text("random mutator")
            }
            Button(onClick = {configID.value=11}, modifier = Modifier.width(250.dp)){
                Text("crossover")
            }
            Button(onClick = {
                configID.value=13
                ConfigData.saveConfigFile()
                             }, modifier = Modifier.width(250.dp)){
                Text("Back and save")
            }
        }

    }
}

@Composable
fun commonScreen(configID:MutableState<Int>){
    var common = ConfigData.common
    var num_periodic_gen_to_check_config by remember {
        mutableStateOf(TextFieldValue("${common.num_periodic_gen_to_check_config}"))
    }
    var num_init_genomes by remember {
        mutableStateOf(TextFieldValue("${common.num_init_genomes}"))
    }
    var max_num_genomes by remember{
        mutableStateOf(TextFieldValue("${common.max_num_genomes}"))
    }
    var is_running = remember{ mutableStateOf(common.is_running) }

    var is_load_genome = remember{ mutableStateOf(common.is_load_genome) }

    var genome_folder by remember {
        mutableStateOf(TextFieldValue("${common.genome_folder}"))
    }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Common Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = num_periodic_gen_to_check_config,
                onValueChange ={num_periodic_gen_to_check_config = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("num periodic gen to check config") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "Num periodic gen to check",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row {
            OutlinedTextField(
                value = num_init_genomes,
                onValueChange ={num_init_genomes = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("num init genomes") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "Num initial genomes",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_num_genomes,
                onValueChange ={max_num_genomes = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max num genomes") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "Max num genomes",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Checkbox(
                checked = is_running.value,
                onCheckedChange = {is_running.value = it},
            )
            Text("is running", modifier = Modifier.padding(16.dp), color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Checkbox(
                checked = is_load_genome.value,
                onCheckedChange = {is_load_genome.value = it},
            )
            Text("is load genome", modifier = Modifier.padding(16.dp), color = Color.White)
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row {
            OutlinedTextField(
                value = genome_folder,
                onValueChange ={genome_folder = it},
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("genome folder") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "Genome folder",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {ConfigData.saveCommon(
                num_periodic_gen_to_check_config.text.toInt(),
                num_init_genomes.text.toInt(),
                max_num_genomes.text.toInt(),
                is_running.value,
                is_load_genome.value,
                genome_folder.text,
                persistent_genome(),
            )
                ConfigData.saveConfigFile()
                configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back and save")
            }
        }


    }
}

@Composable
fun create_genomeScreen(configID:MutableState<Int>){
    var create_genome = ConfigData.create_genome
    var precision by remember {
        mutableStateOf(TextFieldValue("${create_genome.precision}"))
    }
    var p_min_keep_target by remember {
        mutableStateOf(TextFieldValue("${create_genome.p_min_keep_target}"))
    }
    var p_max_keep_target by remember {
        mutableStateOf(TextFieldValue("${create_genome.p_max_keep_target}"))
    }
    var max_scale by remember {
        mutableStateOf(TextFieldValue("${create_genome.max_scale}"))
    }
    var min_value by remember {
        mutableStateOf(TextFieldValue("${create_genome.min_value}"))
    }
    var max_value by remember {
        mutableStateOf(TextFieldValue("${create_genome.max_value}"))
    }
    var min_learning_rate by remember{
        mutableStateOf(TextFieldValue("${create_genome.min_learning_rate}"))
    }
    var max_learning_rate by remember{
        mutableStateOf(TextFieldValue("${create_genome.max_learning_rate}"))
    }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Create Genome Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = precision,
                onValueChange ={precision = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("precision") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "precision",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = p_min_keep_target,
                onValueChange ={p_min_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p_min_keep_target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p_min_keep_target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = p_max_keep_target,
                onValueChange ={p_max_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p_max_keep_target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p_max_keep_target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = max_scale,
                onValueChange ={max_scale = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max scale") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max scale",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = min_value,
                onValueChange ={min_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = max_value,
                onValueChange ={max_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = min_learning_rate,
                onValueChange ={min_learning_rate = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min learning rate") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min learning rate",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = max_learning_rate,
                onValueChange ={max_learning_rate = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max learning rate") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max learning rate",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("back")
            }
            Button(onClick = {
                ConfigData.saveCreate_genome(precision.text.toDouble(),p_min_keep_target.text.toDouble(),p_max_keep_target.text.toDouble(),max_scale.text.toInt(),min_value.text.toDouble(),max_value.text.toDouble(),min_learning_rate.text.toDouble(), max_learning_rate.text.toDouble())
                ConfigData.saveConfigFile()
                configID.value=0
            }, modifier = Modifier.padding(8.dp)){
                Text("back and save")
            }
        }

    }
}

@Composable
fun evaluatorScreen(configID:MutableState<Int>){
    var evaluator = ConfigData.evaluator
    var w_current by remember {
        mutableStateOf(TextFieldValue("${evaluator.w_current}"))
    }
    var w_last by remember {
        mutableStateOf(TextFieldValue("${evaluator.w_last}"))
    }
    var batch_size by remember {
        mutableStateOf(TextFieldValue("${evaluator.batch_size}"))
    }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Evaluator Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = w_current,
                onValueChange ={w_current = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w current") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight current",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = w_last,
                onValueChange ={w_last = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w last") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight last",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = batch_size,
                onValueChange ={batch_size = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("batch size") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "batch size",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("back")
            }
            Button(onClick = {
                             ConfigData.saveEvaluator(w_current.text.toDouble(),w_last.text.toDouble(),batch_size.text.toInt())
                ConfigData.saveConfigFile()
                configID.value=0
            }, modifier = Modifier.padding(8.dp)){
                Text("back and save")
            }
        }

    }
}

@Composable
fun adaptive_evo_ops_selectorScreen(configID: MutableState<Int>){
    var adaptive_evo_ops_selector = ConfigData.adaptive_evo_ops_selector
    var w_delta_mutation by remember {
        mutableStateOf(TextFieldValue("${adaptive_evo_ops_selector.w_delta_mutation}"))
    }
    var w_crossover by remember {
        mutableStateOf(TextFieldValue("${adaptive_evo_ops_selector.w_crossover}"))
    }
    var w_random_mutation by remember {
        mutableStateOf(TextFieldValue("${adaptive_evo_ops_selector.w_random_mutation}"))
    }
    var w_delta_mutation_bg by remember {
        mutableStateOf(TextFieldValue("${adaptive_evo_ops_selector.w_delta_mutation}"))
    }
    var w_crossover_bg by remember {
        mutableStateOf(TextFieldValue("${adaptive_evo_ops_selector.w_crossover_bg}"))
    }
    var w_random_mutation_bg by remember {
        mutableStateOf(TextFieldValue("${adaptive_evo_ops_selector.w_random_mutation_bg}"))
    }
    var w_sim_pruner by remember {
        mutableStateOf(TextFieldValue("${adaptive_evo_ops_selector.w_sim_pruner}"))
    }
    var w_dead_genome_pruner by remember {
        mutableStateOf(TextFieldValue("${adaptive_evo_ops_selector.w_dead_genome_pruner}"))
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Adaptive evo ops selector Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = w_delta_mutation,
                onValueChange ={w_delta_mutation = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w delta mutation") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight delta mutation",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = w_crossover,
                onValueChange ={w_crossover = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w crossover") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight crossover",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = w_random_mutation,
                onValueChange ={w_random_mutation = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w random mutation") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight random mutation",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = w_delta_mutation_bg,
                onValueChange ={w_delta_mutation_bg = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w delta mutation bg") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight delta mutation bg",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = w_crossover_bg,
                onValueChange ={w_crossover_bg = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w crossover mutation bg") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight crossover mutation bg",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = w_random_mutation_bg,
                onValueChange ={w_random_mutation_bg = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w random mutation bg") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight random mutation bg",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = w_sim_pruner,
                onValueChange ={w_sim_pruner = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w sim pruner") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight similar pruner",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = w_dead_genome_pruner,
                onValueChange ={w_dead_genome_pruner = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("w dead genome pruner") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "weight dead genome pruner",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row {
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {
                             ConfigData.saveAdaptive_evo_ops_selector(w_delta_mutation.text.toInt(),w_crossover.text.toInt(),w_random_mutation.text.toInt(),w_delta_mutation_bg.text.toInt(),w_crossover_bg.text.toInt(),w_random_mutation_bg.text.toInt(),w_sim_pruner.text.toInt(),w_dead_genome_pruner.text.toInt())
                ConfigData.saveConfigFile()
                configID.value=0
            }, modifier = Modifier.padding(8.dp)){
                Text("Back and save")
            }
        }

    }
}

@Composable
fun replace_dead_genomeScreen(configID: MutableState<Int>){
    var replace_dead_genome = ConfigData.replace_dead_genome
    var max_age by remember {
        mutableStateOf(TextFieldValue("${replace_dead_genome.max_age}"))
    }
    var precision by remember {
        mutableStateOf(TextFieldValue("${replace_dead_genome.precision}"))
    }
    var p_min_keep_target by remember {
        mutableStateOf(TextFieldValue("${replace_dead_genome.p_min_keep_target}"))
    }
    var p_max_keep_target by remember {
        mutableStateOf(TextFieldValue("${replace_dead_genome.p_max_keep_target}"))
    }
    var max_scale by remember {
        mutableStateOf(TextFieldValue("${replace_dead_genome.max_scale}"))
    }
    var min_value by remember {
        mutableStateOf(TextFieldValue("${replace_dead_genome.min_value}"))
    }
    var max_value by remember {
        mutableStateOf(TextFieldValue("${replace_dead_genome.max_value}"))
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Replace dead genome Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = max_age,
                onValueChange ={max_age = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max age") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max age",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = precision,
                onValueChange ={precision = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("precision") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "precision",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_min_keep_target,
                onValueChange ={p_min_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p min keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p min keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_max_keep_target,
                onValueChange ={p_max_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p max keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p max keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_scale,
                onValueChange ={max_scale = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max scale") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max scale",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = min_value,
                onValueChange ={min_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_value,
                onValueChange ={max_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row {
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {ConfigData.saveReplace_dead_genome(max_age.text.toInt(),precision.text.toDouble(),p_min_keep_target.text.toDouble(),p_max_keep_target.text.toDouble(),max_scale.text.toInt(),min_value.text.toDouble(),max_value.text.toDouble())
                ConfigData.saveConfigFile()
                configID.value=0}
                , modifier = Modifier.padding(8.dp)){
                Text("Back and save")
            }
        }

    }
}

@Composable
fun replace_similar_genomeScreen(configID: MutableState<Int>){
    var replace_similar_genome = ConfigData.replace_similar_genome
    var num_rn_gen_to_eval_for_similarity by remember {
        mutableStateOf(TextFieldValue("${replace_similar_genome.num_rn_gen_to_eval_for_similarity}"))
    }
    var min_delta_fitness_to_prune by remember {
        mutableStateOf(TextFieldValue("${replace_similar_genome.min_delta_fitness_to_prune}"))
    }
    var min_delta_parameters_to_prune by remember {
        mutableStateOf(TextFieldValue("${replace_similar_genome.min_delta_parameters_to_prune}"))
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Replace similar genome Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = num_rn_gen_to_eval_for_similarity,
                onValueChange ={num_rn_gen_to_eval_for_similarity = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("num rn gen to eval for similarity") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "num rn gen to eval for similarity",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = min_delta_fitness_to_prune,
                onValueChange ={min_delta_fitness_to_prune = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min delta fitness to prune") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min delta fitness to prune",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = min_delta_parameters_to_prune,
                onValueChange ={min_delta_parameters_to_prune = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min delta parameters to prune") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min delta parameters to prune",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {ConfigData.saveReplace_similar_genome(num_rn_gen_to_eval_for_similarity.text.toInt(),min_delta_fitness_to_prune.text.toDouble(),min_delta_parameters_to_prune.text.toDouble())
                ConfigData.saveConfigFile()
                configID.value=0
            }, modifier = Modifier.padding(8.dp)){
                Text("Back and save")
            }
        }

    }
}

@Composable
fun delta_mutatorScreen(configID: MutableState<Int>){
    var delta_mutator = ConfigData.delta_mutator
    var num_candidat_genomes by remember {
        mutableStateOf(TextFieldValue("${delta_mutator.num_candidat_genomes}"))
    }
    var num_delta_to_use by remember {
        mutableStateOf(TextFieldValue("${delta_mutator.num_delta_to_use}"))
    }
    var precision by remember {
        mutableStateOf(TextFieldValue("${delta_mutator.precision}"))
    }
    var p_min_keep_target by remember {
        mutableStateOf(TextFieldValue("${delta_mutator.p_min_keep_target}"))
    }
    var p_max_keep_target by remember {
        mutableStateOf(TextFieldValue("${delta_mutator.p_max_keep_target}"))
    }
    var max_scale by remember {
        mutableStateOf(TextFieldValue("${delta_mutator.max_scale}"))
    }
    var min_value by remember {
        mutableStateOf(TextFieldValue("${delta_mutator.min_value}"))
    }
    var max_value by remember {
        mutableStateOf(TextFieldValue("${delta_mutator.max_value}"))
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Delta mutator Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = num_candidat_genomes,
                onValueChange ={num_candidat_genomes = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("num candidat genomes") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "num candidat genomes",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = num_delta_to_use,
                onValueChange ={num_delta_to_use = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("num delta to use") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "num delta to use",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = precision,
                onValueChange ={precision = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("precision") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "precision",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_min_keep_target,
                onValueChange ={p_min_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p min keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p min keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_max_keep_target,
                onValueChange ={p_max_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p max keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p max keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_scale,
                onValueChange ={max_scale = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max scale") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max scale",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = min_value,
                onValueChange ={min_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_value,
                onValueChange ={max_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row {
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {ConfigData.saveDelta_mutator(num_candidat_genomes.text.toInt(),num_delta_to_use.text.toInt(),precision.text.toDouble(),p_min_keep_target.text.toDouble(),p_max_keep_target.text.toDouble(),max_scale.text.toInt(),min_value.text.toDouble(),max_value.text.toDouble())
                ConfigData.saveConfigFile()
                configID.value=0
            }, modifier = Modifier.padding(8.dp)){
                Text("Back and save")
            }
        }

    }
}

/*@Composable
fun delta_mutator_bgScreen(configID: MutableState<Int>){
    var delta_mutator_bg = ConfigData.delta_mutator_bg
    var num_candidat_genomes by remember {
        mutableStateOf(TextFieldValue("${delta_mutator_bg.num_candidat_genomes}"))
    }
    var num_delta_to_use by remember {
        mutableStateOf(TextFieldValue("${delta_mutator_bg.num_delta_to_use}"))
    }
    var precision by remember {
        mutableStateOf(TextFieldValue("${delta_mutator_bg.precision}"))
    }
    var p_min_keep_target by remember {
        mutableStateOf(TextFieldValue("${delta_mutator_bg.p_min_keep_target}"))
    }
    var p_max_keep_target by remember {
        mutableStateOf(TextFieldValue("${delta_mutator_bg.p_max_keep_target}"))
    }
    var max_scale by remember {
        mutableStateOf(TextFieldValue("${delta_mutator_bg.max_scale}"))
    }
    var min_value by remember {
        mutableStateOf(TextFieldValue("${delta_mutator_bg.min_value}"))
    }
    var max_value by remember {
        mutableStateOf(TextFieldValue("${delta_mutator_bg.max_value}"))
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Delta mutator bg Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = num_candidat_genomes,
                onValueChange ={num_candidat_genomes = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("num candidat genomes") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "num candidat genomes",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = num_delta_to_use,
                onValueChange ={num_delta_to_use = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("num delta to use") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "num delta to use",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = precision,
                onValueChange ={precision = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("precision") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "precision",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_min_keep_target,
                onValueChange ={p_min_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p min keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p min keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_max_keep_target,
                onValueChange ={p_max_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p max keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p max keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_scale,
                onValueChange ={max_scale = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max scale") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max scale",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = min_value,
                onValueChange ={min_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_value,
                onValueChange ={max_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row {
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {ConfigData.saveDelta_mutator(num_candidat_genomes.text.toInt(),num_delta_to_use.text.toInt(),precision.text.toDouble(),p_min_keep_target.text.toDouble(),p_max_keep_target.text.toDouble(),max_scale.text.toInt(),min_value.text.toDouble(),max_value.text.toDouble(),true)
                configID.value=0
            }, modifier = Modifier.padding(8.dp)){
                Text("Back and save")
            }
        }
    }
}*/

@Composable
fun random_mutatorScreen(configID: MutableState<Int>){
    var random_mutator = ConfigData.random_mutator
    var precision by remember {
        mutableStateOf(TextFieldValue("${random_mutator.precision}"))
    }
    var p_min_keep_target by remember {
        mutableStateOf(TextFieldValue("${random_mutator.p_min_keep_target}"))
    }
    var p_max_keep_target by remember {
        mutableStateOf(TextFieldValue("${random_mutator.p_max_keep_target}"))
    }
    var max_scale by remember {
        mutableStateOf(TextFieldValue("${random_mutator.max_scale}"))
    }
    var min_value by remember {
        mutableStateOf(TextFieldValue("${random_mutator.min_value}"))
    }
    var max_value by remember {
        mutableStateOf(TextFieldValue("${random_mutator.max_value}"))
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Random mutator Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = precision,
                onValueChange ={precision = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("precision") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "precision",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_min_keep_target,
                onValueChange ={p_min_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p min keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p min keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_max_keep_target,
                onValueChange ={p_max_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p max keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p max keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_scale,
                onValueChange ={max_scale = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max scale") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max scale",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = min_value,
                onValueChange ={min_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_value,
                onValueChange ={max_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {ConfigData.saveRandom_mutator(precision.text.toDouble(),p_min_keep_target.text.toDouble(),p_max_keep_target.text.toDouble(),max_scale.text.toInt(),min_value.text.toDouble(),max_value.text.toDouble())
                ConfigData.saveConfigFile()
                configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back and save")
            }
        }

    }
}

/*@Composable
fun random_mutator_bgScreen(configID: MutableState<Int>){
    var random_mutator_bg = ConfigData.random_mutator
    var precision by remember {
        mutableStateOf(TextFieldValue("${random_mutator_bg.precision}"))
    }
    var p_min_keep_target by remember {
        mutableStateOf(TextFieldValue("${random_mutator_bg.p_min_keep_target}"))
    }
    var p_max_keep_target by remember {
        mutableStateOf(TextFieldValue("${random_mutator_bg.p_max_keep_target}"))
    }
    var max_scale by remember {
        mutableStateOf(TextFieldValue("${random_mutator_bg.max_scale}"))
    }
    var min_value by remember {
        mutableStateOf(TextFieldValue("${random_mutator_bg.min_value}"))
    }
    var max_value by remember {
        mutableStateOf(TextFieldValue("${random_mutator_bg.max_value}"))
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Random mutator bg Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = precision,
                onValueChange ={precision = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("precision") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "precision",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_min_keep_target,
                onValueChange ={p_min_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p min keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p min keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_max_keep_target,
                onValueChange ={p_max_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p max keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p max keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_scale,
                onValueChange ={max_scale = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max scale") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max scale",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = min_value,
                onValueChange ={min_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_value,
                onValueChange ={max_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {ConfigData.saveRandom_mutator(precision.text.toDouble(),p_min_keep_target.text.toDouble(),p_max_keep_target.text.toDouble(),max_scale.text.toInt(),min_value.text.toDouble(),max_value.text.toDouble(),true)
                configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back and save")
            }
        }
    }
}*/

@Composable
fun crossoverScreen(configID: MutableState<Int>){
    var crossover = ConfigData.crossover
    var num_candidat_genomes by remember {
        mutableStateOf(TextFieldValue("${crossover.num_candidat_genomes}"))
    }
    var p_min_keep_target by remember {
        mutableStateOf(TextFieldValue("${crossover.p_min_keep_target}"))
    }
    var p_max_keep_target by remember {
        mutableStateOf(TextFieldValue("${crossover.p_max_keep_target}"))
    }
    var min_value by remember {
        mutableStateOf(TextFieldValue("${crossover.min_value}"))
    }
    var max_value by remember {
        mutableStateOf(TextFieldValue("${crossover.max_value}"))
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Crossover Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = num_candidat_genomes,
                onValueChange ={num_candidat_genomes = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("num candidat genomes") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "num candidat genomes",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_min_keep_target,
                onValueChange ={p_min_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p min keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p min keep targer",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_max_keep_target,
                onValueChange ={p_max_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p max keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p max keep targer",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = min_value,
                onValueChange ={min_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_value,
                onValueChange ={max_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {ConfigData.saveCrossover(num_candidat_genomes.text.toInt(),p_min_keep_target.text.toDouble(),p_max_keep_target.text.toDouble(),min_value.text.toDouble(),max_value.text.toDouble())
                ConfigData.saveConfigFile()
                configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
        }
    }
}

/*@Composable
fun crossover_bgScreen(configID: MutableState<Int>){
    var crossover_bg = ConfigData.crossover_bg
    var num_candidat_genomes by remember {
        mutableStateOf(TextFieldValue("${crossover_bg.num_candidat_genomes}"))
    }
    var p_min_keep_target by remember {
        mutableStateOf(TextFieldValue("${crossover_bg.p_min_keep_target}"))
    }
    var p_max_keep_target by remember {
        mutableStateOf(TextFieldValue("${crossover_bg.p_max_keep_target}"))
    }
    var min_value by remember {
        mutableStateOf(TextFieldValue("${crossover_bg.min_value}"))
    }
    var max_value by remember {
        mutableStateOf(TextFieldValue("${crossover_bg.max_value}"))
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Crossover bg Config",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            OutlinedTextField(
                value = num_candidat_genomes,
                onValueChange ={num_candidat_genomes = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("num candidat genomes") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "num candidat genomes",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_min_keep_target,
                onValueChange ={p_min_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p min keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p min keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = p_max_keep_target,
                onValueChange ={p_max_keep_target = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("p max keep target") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "p max keep target",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = min_value,
                onValueChange ={min_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("min value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "min value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            OutlinedTextField(
                value = max_value,
                onValueChange ={max_value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("max value") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "max value",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row{
            Button(onClick = {configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back")
            }
            Button(onClick = {ConfigData.saveCrossover(num_candidat_genomes.text.toInt(),p_min_keep_target.text.toDouble(),p_max_keep_target.text.toDouble(),min_value.text.toDouble(),max_value.text.toDouble(),true)
                configID.value=0}, modifier = Modifier.padding(8.dp)){
                Text("Back and save")
            }
        }
    }
}*/