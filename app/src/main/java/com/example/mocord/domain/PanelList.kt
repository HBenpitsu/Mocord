package com.example.mocord.domain

/* currently this is a mock
* TODO: implement the actual PanelList
*  */

class PanelList {
    private val _panels = mutableListOf<Panel>()

    init {}

    fun loadState() {
        _panels.clear()
        _panels.add(LabelPanel("label1"))
        _panels.add(LabelPanel("label2"))
        _panels.add(LabelPanel("label3"))
        _panels.add(LabelPanel("label4"))
        _panels.add(LabelPanel("label5"))
        _panels.add(LabelPanel("label6"))
        _panels.add(LabelPanel("label7"))
        _panels.add(LabelPanel("label8"))
    }

    fun saveState() {
        // TODO: save the state of the panels
    }
}
