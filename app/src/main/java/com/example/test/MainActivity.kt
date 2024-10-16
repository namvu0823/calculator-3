package com.example.test

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView

    var state: Int = 1 // Trạng thái của máy tính: 1 là nhập số thứ nhất, 2 là nhập số thứ hai
    var op: Int = 0 // Biến lưu phép toán: 1=+, 2=-, 3=*, 4=/
    var op1: Int = 0 // Số thứ nhất
    var op2: Int = 0 // Số thứ hai

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.text_result)

        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)

        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnX).setOnClickListener(this)
        findViewById<Button>(R.id.btnDivide).setOnClickListener(this)

        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)
        findViewById<Button>(R.id.btnBS).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id

        when (id) {
            R.id.btn0 -> addDigit(0)
            R.id.btn1 -> addDigit(1)
            R.id.btn2 -> addDigit(2)
            R.id.btn3 -> addDigit(3)
            R.id.btn4 -> addDigit(4)
            R.id.btn5 -> addDigit(5)
            R.id.btn6 -> addDigit(6)
            R.id.btn7 -> addDigit(7)
            R.id.btn8 -> addDigit(8)
            R.id.btn9 -> addDigit(9)
            R.id.btnAdd -> setOperation(1) // +
            R.id.btnSub -> setOperation(2) // -
            R.id.btnX -> setOperation(3) // *
            R.id.btnDivide -> setOperation(4) // /
            R.id.btnEqual -> calculateResult()
            R.id.btnC -> clearAll()
            R.id.btnCE -> clearEntry()
            R.id.btnBS -> backspace()
        }
    }
    private  var expression=""
    // Thêm chữ số vào số hiện tại
    private fun addDigit(digit: Int) {
        if (state == 1) {
            op1 = op1 * 10 + digit
            textResult.text = "$op1"
        } else {
            op2 = op2 * 10 + digit
            textResult.text = "$expression $op2"
        }
    }

    // Thiết lập phép toán
    private fun setOperation(operation: Int) {

        if (state == 1) {
            op = operation
            val operatorSymbol=when(operation){
                1 -> "+"
                2 -> "-"
                3 -> "x"
                4 -> "/"
                else -> ""
            }
            expression="$op1 $operatorSymbol"
            textResult.text=expression
            state = 2 // Chuyển sang nhập số thứ hai
        }

    }

    // Tính toán kết quả
    private fun calculateResult() {
        var result = 0
        var operatorSymbol = ""
        when (op) {
            1 -> {
                result = op1 + op2 // Cộng
                operatorSymbol="+"
            }
            2 -> {
                result = op1 - op2 // Trừ
                operatorSymbol="-"
            }
            3 -> {
                result = op1 * op2 // Nhân
                operatorSymbol="x"
            }
            4 -> {
                // Kiểm tra chia cho 0
                if (op2 != 0) {
                    result = op1 / op2 // Chia
                    operatorSymbol="/"
                } else {
                    textResult.text = "Error"
                    return
                }
            }
        }
        val fullExpression = "$expression $op2 = $result"

        // Hiển thị chuỗi này trong TextView
        textResult.text = fullExpression
        state = 1
        op1 = result // Giữ lại kết quả để tiếp tục phép tính mới
        op2 = 0
        op = 0
        expression=""//reset biểu thức tạm thời
    }

    // Xóa tất cả dữ liệu
    private fun clearAll() {
        op1 = 0
        op2 = 0
        op = 0
        state = 1
        textResult.text = "0"
    }

    // Xóa số vừa nhập
    private fun clearEntry() {
        if (state == 1) {
            op1 = 0
            textResult.text = "0"
        } else {
            op2 = 0
            textResult.text = "0"
        }
    }

    // Xóa một chữ số
    private fun backspace() {
        if (state == 1) {
            op1 = op1 / 10
            textResult.text = "$op1"
        } else {
            op2 = op2 / 10
            textResult.text = "$op2"
        }
    }
}
