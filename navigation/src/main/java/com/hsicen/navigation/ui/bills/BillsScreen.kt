package com.hsicen.navigation.ui.bills

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import com.hsicen.navigation.R
import com.hsicen.navigation.data.Bill
import com.hsicen.navigation.ui.components.BillRow
import com.hsicen.navigation.ui.components.StatementBody

/**
 * The Bills screen.
 */
@Composable
fun BillsBody(bills: List<Bill>) {
    StatementBody(
        modifier = Modifier.clearAndSetSemantics { contentDescription = "Bills" },
        items = bills,
        amounts = { bill -> bill.amount },
        colors = { bill -> bill.color },
        amountsTotal = bills.map { bill -> bill.amount }.sum(),
        circleLabel = stringResource(R.string.due),
        rows = { bill ->
            BillRow(bill.name, bill.due, bill.amount, bill.color)
        }
    )
}
