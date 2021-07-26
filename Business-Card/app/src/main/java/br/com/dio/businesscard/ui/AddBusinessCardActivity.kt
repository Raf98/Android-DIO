package br.com.dio.businesscard.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.businesscard.App
import br.com.dio.businesscard.R
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.databinding.ActivityAddBusinessCardBinding
import java.lang.StringBuilder

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val colorsList: List<String> = mutableListOf<String>(
        "red",
        "blue",
        "green",
        "black",
        "white",
        "gray",
        "cyan",
        "magenta",
        "yellow",
        "aqua",
        "fuchsia",
        "lime",
        "maroon",
        "navy",
        "olive",
        "purple",
        "silver",
        "teal"
    )

    private val ptColorsList: List<String> = mutableListOf<String>(
        "vermelho",
        "azul",
        "verde",
        "preto",
        "branco",
        "cinza",
        "ciano",
        "magenta",
        "amarelo",
        "aqua",
        "fucsia",
        "lima",
        "marrom",
        "naval",
        "oliva",
        "roxo",
        "prateado",
        "verde-azulado"
    )

    private val ptToEnColorsMap: HashMap<String, String> = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fillColorsMap()
        setupAutoCompleteView()
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = binding.tilCor.editText?.text.toString().uppercase()
            )

            var errorsStr: String = checkInvalidInputs(businessCard)

            if (errorsStr == " ") {
                if (!businessCard.fundoPersonalizado.contains("#")) {
                    businessCard.fundoPersonalizado =
                        ptToEnColorsMap[businessCard.fundoPersonalizado.lowercase()].toString()
                }
                mainViewModel.insert(businessCard)
                Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                errorsStr = errorsStr.substring(0, errorsStr.length - 2) + "."
                Toast.makeText(
                    this,
                    getString(R.string.label_show_insert_base_error) + errorsStr,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun setupAutoCompleteView() {

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.select_dialog_item, ptColorsList
        )
        binding.actvCor.threshold = 1
        binding.actvCor.setAdapter(arrayAdapter)
    }

    private fun fillColorsMap() {
        for (i in ptColorsList.indices) {
            ptToEnColorsMap[ptColorsList[i]] = colorsList[i]
        }
    }

    private fun checkInvalidInputs(businessCard: BusinessCard): String {
        val errorsBuilder = StringBuilder(" ")
        val regexExpressionBuilder = StringBuilder("#")
        for (i in 1..6) {
            regexExpressionBuilder.append("[\\dABCDEF]")
        }
        val colorRegex = Regex(regexExpressionBuilder.toString())

        if (businessCard.nome == "") errorsBuilder.append("nome vazio, ")
        if (businessCard.telefone == "") errorsBuilder.append("telefone vazio, ")
        if (businessCard.email == "") errorsBuilder.append("e-mail vazio, ")
        if (businessCard.empresa == "") errorsBuilder.append("empresa vazia, ")
        if (!(ptColorsList.contains(businessCard.fundoPersonalizado.lowercase()) ||
                    businessCard.fundoPersonalizado.matches(colorRegex))
        )
            errorsBuilder.append("cor inválida, ")

        return errorsBuilder.toString()
    }

}
