package application.model.viewModel.admin;


import application.model.viewModel.common.ChartDataVM;
import application.model.viewModel.common.LayoutHeaderAdminVM;

import java.util.List;

public class ChartVM {

    private LayoutHeaderAdminVM layoutHeaderAdminVM;

    private List<ChartDataVM> chartDataVMS1;

    public List<ChartDataVM> getChartDataVMS2() {
        return chartDataVMS2;
    }

    public void setChartDataVMS2(List<ChartDataVM> chartDataVMS2) {
        this.chartDataVMS2 = chartDataVMS2;
    }

    private List<ChartDataVM> chartDataVMS2;

    public List<ChartDataVM> getChartDataVMS1() {
        return chartDataVMS1;
    }

    public void setChartDataVMS1(List<ChartDataVM> chartDataVMS) {
        this.chartDataVMS1 = chartDataVMS;
    }

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

}
