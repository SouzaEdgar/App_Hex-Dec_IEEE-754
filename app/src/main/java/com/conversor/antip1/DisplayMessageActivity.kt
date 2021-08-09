package com.conversor.antip1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.math.floor
import kotlin.math.pow

class DisplayMessageActivity : AppCompatActivity() {

    private fun posOUneg(e: Double): String {
        var sinal = ""
        sinal += if (e.toString().startsWith("-")) {
            "1"
        } else {
            "0"
        }
        return sinal
    }

    private fun binarioDEC(d: Double): String {
        val partINT = d.toString().replace("-", "").toDouble()
        val inteiro = floor(partINT).toLong()
        val binINT = inteiro.toString(2)
        var decimal = partINT - inteiro
        var binDEC = ""

        //for (x in binINT.length until 24){
        while (decimal > 0.0) {
            val multResultado = decimal * 2.0

            if (multResultado >= 1.0) {
                decimal = multResultado - 1
                binDEC += "1"
            } else {
                decimal = multResultado
                binDEC += "0"
            }
        }
        return "$binINT.$binDEC"
    }

    private fun formExpoente(x: String): String {
        var compara = 0
        var passouDoPonto = false
        val tamanho = x.length
        var expBIN: String

        for (a in 0..tamanho) {
            val array = x[a].toString()
            if (array == "1") {
                compara = a
                break
            } else if (array == ".") {
                passouDoPonto = true
            }
        }

        if (passouDoPonto) {
            compara -= 1 // Aqui ele desconta o valor do "."
            compara = -compara
        }

        val expoente: Int =
            if (passouDoPonto) {                                                // | P = casa do ponto flutuante | U = casa do primeiro um |
                (compara + 127)                // Expoente negativo => E = P + 127
            } else {
                ((x.indexOf(".") - 1) - compara + 127)                //Expoente positivo => E = P - (U + 1) + 127  (este +1 é por conta da casa do ".", visto que o array começa do 0)
            }

        expBIN = expoente.toString(2)

        // Aqui ocorre a correção para 8 casa do Binário (Expoente)
        while (expBIN.length < 8) {
            expBIN = "0$expBIN"
        }
        return expBIN
    }

    private fun formatBin(e: String): String {
        var bin = e
        var depoisUm = 0

        for (x in 0..25) {
            bin += "0"
        } // Aqui é aumentado exponencialmente os valores do binario, para ter material mais que suficiente para a formatação

        val tamanho = bin.length
        for (y in 0..tamanho) {
            val array = bin[y].toString()
            if (array == "1") {
                depoisUm = y + 1
                break
            }
        } // Aqui é localizado o primeiro 1 e, retornado o valor da próxima casa

        var formatado = ""
        for (z in depoisUm until tamanho) {
            val sequencia = bin[z].toString()
            formatado += sequencia

            if (formatado.length >= 23) {
                break
            }
        } // Dado um valor grande para o a formataçao, aqui é remodelado o binario partindo da casa do numero apos o primeiro 1 (aquele -1, serve para caso)
        return formatado
    }

    private fun formHexaDoBin(bin: String): String {
        var hexa = 0
        var casa = 0
        var hexaBIN = ""

        for (element in bin) {
            casa += 1
            val valor = element.toString()
            if (valor == "1") {
                when (casa) {
                    1 -> {
                        hexa += 8
                    }
                    2 -> {
                        hexa += 4
                    }
                    3 -> {
                        hexa += 2
                    }
                    4 -> {
                        hexa += 1
                    } // aqui tem o valor número em hexadecima
                }
            }

            if (casa == 4) { // aqui eu pego essa valor, se ele for acima de 10 ocorre a conversão dele em Letra
                casa = 0
                if (hexa >= 10) {
                    when (hexa) {
                        10 -> {
                            hexaBIN += "a"
                        }
                        11 -> {
                            hexaBIN += "b"
                        }
                        12 -> {
                            hexaBIN += "c"
                        }
                        13 -> {
                            hexaBIN += "d"
                        }
                        14 -> {
                            hexaBIN += "e"
                        }
                        15 -> {
                            hexaBIN += "f"
                        }
                    }
                } else {// Aqui é onde eu junto os valores Numeros(<= 10) ou/e Letras
                    hexaBIN += hexa
                }
                hexa = 0 // Aqui o valor volta a zero para poder pegar os proximos valores
            }
        }
        return hexaBIN
    }

    private fun formBinDoHexa(b: String): String {
        val alfanumerico = listOf("1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "0",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "A",
            "B",
            "C",
            "D",
            "E",
            "F")
        var hexaBIN = ""
        var binarioDoHex = ""

        for (element in b) {
            if (element.toString() in alfanumerico) {
                hexaBIN += element.toString()
            } else {
                print("\n$element é caractere especial\n")
            } // Este laço serve para filtrar o hexadecimal tirando deixando passar somente caracteres numericos e alfabeticos
        }
        for (element in hexaBIN) {
            if (element.toString() in alfanumerico) {
                when (element.toString()) {
                    "0" -> {
                        binarioDoHex += "0000"
                    }
                    "1" -> {
                        binarioDoHex += "0001"
                    }
                    "2" -> {
                        binarioDoHex += "0010"
                    }
                    "3" -> {
                        binarioDoHex += "0011"
                    }
                    "4" -> {
                        binarioDoHex += "0100"
                    }
                    "5" -> {
                        binarioDoHex += "0101"
                    }
                    "6" -> {
                        binarioDoHex += "0110"
                    }
                    "7" -> {
                        binarioDoHex += "0111"
                    }
                    "8" -> {
                        binarioDoHex += "1000"
                    }
                    "9" -> {
                        binarioDoHex += "1001"
                    }
                    "a" -> {
                        binarioDoHex += "1010"
                    }
                    "A" -> {
                        binarioDoHex += "1010"
                    }
                    "b" -> {
                        binarioDoHex += "1011"
                    }
                    "B" -> {
                        binarioDoHex += "1011"
                    }
                    "c" -> {
                        binarioDoHex += "1100"
                    }
                    "C" -> {
                        binarioDoHex += "1100"
                    }
                    "d" -> {
                        binarioDoHex += "1101"
                    }
                    "D" -> {
                        binarioDoHex += "1101"
                    }
                    "e" -> {
                        binarioDoHex += "1110"
                    }
                    "E" -> {
                        binarioDoHex += "1110"
                    }
                    "f" -> {
                        binarioDoHex += "1111"
                    }
                    "F" -> {
                        binarioDoHex += "1111"
                    }
                }
            } else {
                binarioDoHex = "Erro"
            }
        }
        return binarioDoHex
    }

    private fun binarioDoHexadecimal(bin: String): String {
        var cont = 0
        var exp = ""

        for (element in bin) {
            if (cont > 0) {
                exp += element.toString()
            }
            cont += 1
            if (exp.length >= 8) {
                break
            }
        } // Aqui ele retorna o binário do expoente do hexadecimal

        var binExp = exp.toLong()
        var valorDecimal = 0
        var elevado = 0
        var restante: Long

        while (binExp.toInt() != 0) {
            restante = binExp % 10
            binExp /= 10
            valorDecimal += (restante * 2.0.pow(elevado.toDouble())).toInt()
            ++elevado
        } // Aqui ele retorna o valor esperado do expoente do binário do hexadecimal (deve ser feito o calculo: EXP - 127 => Potência)
        //return decimalNumber - 127
        val expoente = valorDecimal - 127
        ////////////////////////////
        //
        var mantissa = "1."
        for (z in 9 until bin.length) {
            mantissa += bin[z].toString()
        } // Aqui é adicionado o "1." á mantissa, para que ela fique correta
        //return mantissa.toString()

        var newMantissa = mantissa.replace(".","")
        var newnewMantissa = ""

        if (expoente.toString().startsWith("-")) { // Caso o valor do expoente seja negativo (ELE DEFINE O LADO QUE A VÌRGULA SERA MOVIDA), caso negativo ela vai para frente, sendo necessario adicionar os 0
            var moverNegativo = expoente*-1
            while (moverNegativo > 1) { // O ultimo 0 vai ser adicionado  de forma manual, portanto esse 0 foi retirado do laço
                newMantissa = "0$newMantissa"
                moverNegativo -= 1
            }
            newMantissa = "0.$newMantissa"
        } else {
            for (h in newMantissa.indices) {
                newnewMantissa += newMantissa[h].toString()
                if (h == expoente) {
                    newnewMantissa += "."
                }
            }
            newMantissa = newnewMantissa
        }
        //return newMantissa.toString()

        val poNTO = newMantissa.indexOf(".")
        val binario = newMantissa
        var antesPonto = ""
        var depoisPonto = ""

        for (w in 0 until poNTO) {
            antesPonto += binario[w].toString()
        }
        for (x in poNTO+1 until binario.length) {
            depoisPonto += binario[x].toString()
        }
        ///////////////ANTES DO PONTO///////////////////////
        var binEXP = antesPonto.toLong()
        var valorDECIMAL = 0
        var elEVADO = 0
        var resTANTE: Long

        while (binEXP.toInt() != 0) {
            resTANTE = binEXP % 10
            binEXP /= 10
            valorDECIMAL += (resTANTE * 2.0.pow(elEVADO.toDouble())).toInt()
            ++elEVADO
        }
        //////////////DEPOIS DO PONTO///////////////////////
        var decimalNumber = 0.0

        for (a in depoisPonto.indices) { // COLOCAR O BINARIO SEM O 0, ISSO SÓ ATRAPALHA
            if (depoisPonto[a].toString() == "1") {
                decimalNumber += 2.0.pow(-a - 1.toDouble()) // Ja que inicia no 0, é preciso somar 1 no a, porem ele é negativo então -1 é o correto
            } // verificar se para obter o valor do 0,binario é somente necessario pegar 2^-n , sendo n o as casas que possuem 1
        }
        return if (bin[0].toString() == "1") {
            "-" + (valorDECIMAL + decimalNumber).toString()
        } else {
            (valorDECIMAL + decimalNumber).toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val numero = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", ".")
        val alfabetico = listOf("a", "b", "c", "d", "e", "f", "A", "B", "C", "D", "E", "F","x","X")
        var passe = false
        var somenteNumero = false
        var binario = false
        val valor = intent.getStringExtra(EXTRA_MESSAGE)
        var erro = ""


        for (element in valor.toString()) { // Aqui é verificado se o texto do input possui algum caractere diferente do esperado
            if (element.toString() in numero || element.toString() in alfabetico) {
                passe = true
            } else {
                erro = element.toString()
                passe = false
                break
            }
        }

        if (passe) { // Agora falta especificar se é decimal ou hexadecimal
            for (element in valor.toString()) {
                if (element.toString() in numero && element.toString() !in alfabetico) { // Aqui ele verifica se o valor possui apenas os valores numericos, e nao alfabeticos
                    somenteNumero = true
                } else {
                    somenteNumero = false
                    break
                } // Se possuir letra ele ja sai do
            }
        }

        if (!passe && !somenteNumero) {
            findViewById<TextView>(R.id.textView3).apply {
                """  ERRO
     O caractere $erro é inválido""".trimIndent().also { text = it }}
        } else {
            if (somenteNumero) { // Aqui vai servir para separar de vez o binario do hexadecimal
                for (element in valor.toString()) {
                    if (element.toString() in numero) {
                        binario = true
                    } else {
                        binario = false
                        break
                    }
                }
            }


            if (binario) {
                val decimal = valor!!.toDouble()
                val bin = binarioDEC(decimal)
                val semPonto = bin.replace(".", "") // tirar o "." do binário
                val sinal =
                    posOUneg(decimal)    // Ele verifica se o número é positivo ou negativo, retornando assim 0 ou 1
                val expoenteBin = formExpoente(bin) // Usar o binário que tem "."

                val ieee = "$sinal$expoenteBin${formatBin(semPonto)}"
                val hexaBin = formHexaDoBin(ieee)

                findViewById<TextView>(R.id.textView3).apply {
                    """
            Binario:		                $bin
            
            Binario formatado:	                ${formatBin(semPonto)}
            
            
            
            
            IEEE-754:		                $ieee
            Hexadecimal:		0x$hexaBin
            
            
            
            
            
            
            
                    (formatação a menor)
        """.trimIndent().also { text = it }
                }
                //findViewById<TextView>(R.id.textView3).apply {"numero".trimIndent().also { text = it }}
            } else {
                val hexadecimal = valor.toString()
                var newHexadecimal = ""

                for (a in 2 until hexadecimal.length) {
                    newHexadecimal += hexadecimal[a].toString()
                }

                val newBinDoHexa = formBinDoHexa(newHexadecimal)
                val binDoHexa = formBinDoHexa(hexadecimal)

                if (hexadecimal.startsWith("0x") || hexadecimal.startsWith("0X")) {
                    findViewById<TextView>(R.id.textView3).apply {
                        "Decimal: ${
                            binarioDoHexadecimal(newBinDoHexa)
                        }".also { text = it }
                    }
                } else {
                    findViewById<TextView>(R.id.textView3).apply {
                        //"Binário: ${formBinDoHexa(hexadecimal)}".also {
                        "Decimal: ${binarioDoHexadecimal(binDoHexa)}".also {
                            text = it
                        }
                    }
                }
            }
        }


    }
}
