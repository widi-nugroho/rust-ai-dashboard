package model

import com.google.gson.Gson
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

class persistent_genome()


data class crossover(
    var num_candidat_genomes: Int,
    var p_min_keep_target: Double,
    var p_max_keep_target: Double,
    var min_value: Double,
    var max_value: Double
)
/*data class crossover_bg(
    var num_candidat_genomes: Int,
    var p_min_keep_target: Double,
    var p_max_keep_target: Double,
    var min_value: Double,
    var max_value: Double
)*/
data class random_mutator(
    var precision: Double,
    var p_min_keep_target: Double,
    var p_max_keep_target: Double,
    var max_scale: Int,
    var min_value: Double,
    var max_value: Double
)
/*data class random_mutator_bg(
    var precision: Double,
    var p_min_keep_target: Double,
    var p_max_keep_target: Double,
    var max_scale: Int,
    var min_value: Double,
    var max_value: Double
)*/
/*data class delta_mutator_bg(
    var num_candidat_genomes: Int,
    var num_delta_to_use: Int,
    var precision: Double,
    var p_min_keep_target: Double,
    var p_max_keep_target: Double,
    var max_scale: Int,
    var min_value: Double,
    var max_value: Double
)*/
data class delta_mutator(
    var num_candidat_genomes: Int,
    var num_delta_to_use: Int,
    var precision: Double,
    var p_min_keep_target: Double,
    var p_max_keep_target: Double,
    var max_scale: Int,
    var min_value: Double,
    var max_value: Double
)
data class replace_similar_genome(
    var num_rn_gen_to_eval_for_similarity: Int,
    var min_delta_fitness_to_prune: Double,
    var min_delta_parameters_to_prune: Double
)
data class replace_dead_genome(
    var max_age: Int,
    var precision: Double,
    var p_min_keep_target: Double,
    var p_max_keep_target: Double,
    var max_scale: Int,
    var min_value: Double,
    var max_value: Double
)
data class adaptive_evo_ops_selector(
    var w_delta_mutation: Int,
    var w_crossover: Int,
    var w_random_mutation: Int,
    var w_delta_mutation_bg: Int,
    var w_crossover_bg: Int,
    var w_random_mutation_bg: Int,
    var w_sim_pruner: Int,
    var w_dead_genome_pruner: Int
)
data class evaluator(
    var w_current: Double,
    var w_last: Double,
    var batch_size: Int
)
data class create_genome(
    var precision: Double,
    var p_min_keep_target: Double,
    var p_max_keep_target: Double,
    var max_scale: Int,
    var min_value: Double,
    var max_value: Double,
    var min_learning_rate: Double,
    var max_learning_rate: Double
)
data class common(
    var num_periodic_gen_to_check_config: Int,
    var num_init_genomes: Int,
    var max_num_genomes: Int,
    var is_running: Boolean,
    var is_load_genome: Boolean,
    var genome_folder: String,
    var persistent_genome: persistent_genome,
)
data class config(
    var num_periodic_gen_to_check_config: Int,
    var num_init_genomes: Int,
    var max_num_genomes: Int,
    var is_running: Boolean,
    var is_load_genome: Boolean,
    var genome_folder: String,
    var persistent_genome: persistent_genome,
    var create_genome: create_genome,
    var evaluator: evaluator,
    var adaptive_evo_ops_selector: adaptive_evo_ops_selector,
    var replace_dead_genome: replace_dead_genome,
    var replace_similar_genome: replace_similar_genome,
    var delta_mutator: delta_mutator,
    //var delta_mutator_bg: delta_mutator_bg,
    var random_mutator: random_mutator,
    //var random_mutator_bg: random_mutator_bg,
    var crossover: crossover,
    //var crossover_bg: crossover_bg
)

object ConfigData {
    val gson = Gson()
    var json = ""
    lateinit var ConfigClass:config

    //data
    lateinit var crossover:crossover
    //lateinit var crossover_bg: crossover_bg
    lateinit var random_mutator: random_mutator
    //lateinit var random_mutator_bg: random_mutator_bg
    lateinit var delta_mutator: delta_mutator
    //lateinit var delta_mutator_bg: delta_mutator_bg
    lateinit var replace_similar_genome: replace_similar_genome
    lateinit var replace_dead_genome: replace_dead_genome
    lateinit var adaptive_evo_ops_selector: adaptive_evo_ops_selector
    lateinit var evaluator: evaluator
    lateinit var create_genome: create_genome
    lateinit var common: common
    var path: String =""
    fun saveCommon(num_periodic_gen_to_check_config: Int, num_init_genomes: Int, max_num_genomes: Int,
                   is_running: Boolean, is_load_genome: Boolean, genome_folder: String,
                   persistent_genome: persistent_genome){
        val t = common(num_periodic_gen_to_check_config, num_init_genomes, max_num_genomes, is_running, is_load_genome, genome_folder, persistent_genome)
        common = t
    }
    fun saveCreate_genome(precision: Double,p_min_keep_target: Double,p_max_keep_target: Double,max_scale: Int,min_value: Double,max_value: Double,min_learning_rate:Double,max_learning_rate:Double){
        val t = create_genome(precision, p_min_keep_target, p_max_keep_target, max_scale, min_value, max_value,min_learning_rate,max_learning_rate)
        create_genome = t
    }
    fun saveEvaluator(w_current: Double,w_last: Double,batch_size: Int){
        val t = evaluator(w_current, w_last, batch_size)
        evaluator = t
    }
    fun saveAdaptive_evo_ops_selector(w_delta_mutation: Int,w_crossover: Int,w_random_mutation: Int,w_delta_mutation_bg: Int,w_crossover_bg: Int,w_random_mutation_bg: Int,w_sim_pruner: Int,w_dead_genome_pruner: Int){
        val t = adaptive_evo_ops_selector(w_delta_mutation, w_crossover, w_random_mutation, w_delta_mutation_bg, w_crossover_bg, w_random_mutation_bg, w_sim_pruner, w_dead_genome_pruner)
        adaptive_evo_ops_selector = t
    }
    fun saveReplace_dead_genome(max_age: Int,precision: Double,p_min_keep_target: Double,p_max_keep_target: Double,max_scale: Int,min_value: Double,max_value: Double){
        val t = replace_dead_genome(max_age, precision, p_min_keep_target, p_max_keep_target, max_scale, min_value, max_value)
        replace_dead_genome = t
    }
    fun saveReplace_similar_genome(num_rn_gen_to_eval_for_similarity: Int,min_delta_fitness_to_prune: Double,min_delta_parameters_to_prune: Double){
        val t = replace_similar_genome(num_rn_gen_to_eval_for_similarity, min_delta_fitness_to_prune, min_delta_parameters_to_prune)
        replace_similar_genome = t
    }
    fun saveDelta_mutator(num_candidat_genomes: Int,num_delta_to_use:Int,precision: Double,p_min_keep_target: Double,p_max_keep_target: Double,max_scale: Int,min_value: Double,max_value: Double){
        val t = delta_mutator(num_candidat_genomes, num_delta_to_use, precision, p_min_keep_target, p_max_keep_target, max_scale, min_value, max_value)
        delta_mutator = t
    }
    fun saveRandom_mutator(precision: Double,p_min_keep_target: Double,p_max_keep_target: Double,max_scale: Int,min_value: Double,max_value: Double){
        val t = random_mutator(precision, p_min_keep_target, p_max_keep_target, max_scale, min_value, max_value)
        random_mutator = t
    }
    fun saveCrossover(num_candidat_genomes: Int,p_min_keep_target: Double,p_max_keep_target: Double,min_value: Double,max_value: Double){
        val t = crossover(num_candidat_genomes, p_min_keep_target, p_max_keep_target, min_value, max_value)
        crossover = t

    }


    fun loadConfigFile(path:String){
        this.path=path
        json = File(path).readText(Charsets.UTF_8)
        ConfigClass = gson.fromJson(json,config::class.java)
        crossover = ConfigClass.crossover
        //crossover_bg = ConfigClass.crossover_bg
        random_mutator = ConfigClass.random_mutator
        //random_mutator_bg = ConfigClass.random_mutator_bg
        delta_mutator = ConfigClass.delta_mutator
        //delta_mutator_bg = ConfigClass.delta_mutator_bg
        replace_similar_genome = ConfigClass.replace_similar_genome
        replace_dead_genome = ConfigClass.replace_dead_genome
        adaptive_evo_ops_selector = ConfigClass.adaptive_evo_ops_selector
        evaluator = ConfigClass.evaluator
        create_genome = ConfigClass.create_genome
        common = common(ConfigClass.num_periodic_gen_to_check_config, ConfigClass.num_init_genomes, ConfigClass.max_num_genomes,
            ConfigClass.is_running, ConfigClass.is_load_genome, ConfigClass.genome_folder, ConfigClass.persistent_genome,
            )
    }

    fun saveConfigFile(){
        val gson = Gson()
        var num_periodic_gen_to_check_config = common.num_periodic_gen_to_check_config
        var num_init_genomes = common.num_init_genomes
        var max_num_genomes = common.max_num_genomes
        var is_running = common.is_running
        var is_load_genome = common.is_load_genome
        var genome_folder = common.genome_folder
        var persistent_genome = common.persistent_genome

        var create_genome = ConfigData.create_genome
        var evaluator = ConfigData.evaluator
        var adaptive_evo_ops_selector = ConfigData.adaptive_evo_ops_selector
        var replace_dead_genome = ConfigData.replace_dead_genome
        var replace_similar_genome = ConfigData.replace_similar_genome
        var delta_mutator = ConfigData.delta_mutator
        //var delta_mutator_bg = ConfigData.delta_mutator_bg
        var random_mutator = ConfigData.random_mutator
        //var random_mutator_bg = ConfigData.random_mutator_bg

        var configClass = config(num_periodic_gen_to_check_config, num_init_genomes, max_num_genomes, is_running, is_load_genome, genome_folder, persistent_genome, create_genome, evaluator, adaptive_evo_ops_selector, replace_dead_genome, replace_similar_genome, delta_mutator, random_mutator, crossover)
        var json = gson.toJson(configClass)
        PrintWriter(FileWriter(this.path)).use {
            it.write(json)
        }

    }
}