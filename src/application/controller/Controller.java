package application.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.util.Callback;
import application.model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.applet.Main;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Controller {
    private int port;
    private ArrayList<String> states;
    private DBAction db;
    private TreeItem<Order> root;
    private TreeItem<Order> root1;
    private TreeItem<Order> root2;
    private OrderContainer orderContainer;

    @FXML
    private JFXButton btnConfig;

    @FXML
    private TreeTableView<Order> treeTableView;

    @FXML
    private TreeTableColumn<Stock, Integer> code;

    @FXML
    private TreeTableColumn<Stock, String> name;

    @FXML
    private TreeTableColumn<Stock, Double> quantity;

    @FXML
    private TreeTableColumn<Stock, Double> unitPrice;

    @FXML
    private TreeTableColumn<Stock, Double> totalAmount;

    @FXML
    private TreeTableView<Order> treeTableView1;

    @FXML
    private TreeTableColumn<Stock, Integer> code1;

    @FXML
    private TreeTableColumn<Stock, String> name1;

    @FXML
    private TreeTableColumn<Stock, Double> quantity1;

    @FXML
    private TreeTableColumn<Stock, Double> unitPrice1;

    @FXML
    private TreeTableColumn<Stock, Double> totalAmount1;

    @FXML
    private TreeTableColumn<Order, String> state;

    @FXML
    private TreeTableView<Order> treeTableView2;

    @FXML
    private TreeTableColumn<Stock, Integer> code2;

    @FXML
    private TreeTableColumn<Stock, String> name2;

    @FXML
    private TreeTableColumn<Stock, Double> quantity2;

    @FXML
    private TreeTableColumn<Stock, Double> unitPrice2;

    @FXML
    private TreeTableColumn<Stock, Double> totalAmount2;

    @FXML
    private TreeTableColumn<Order, String> state2;

    @FXML
    void config(ActionEvent event) throws SQLException {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(db.getPort()));

        dialog.setTitle("Тохиргоо");
        dialog.setHeaderText("Порт хаяг оруулна уу !");
        dialog.setContentText("Порт: ");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(port -> {
            this.port = Integer.parseInt(port);
            try {
                db.updatePort(Integer.parseInt(port));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void initialize() throws SQLException {
        orderContainer = OrderContainer.getOrderContainer();
        db = new DBAction();
        root = new TreeItem<>();
        root1 = new TreeItem<>();
        root2 = new TreeItem<>();
        states = new ArrayList<>(Arrays.asList("Хүлээгдэж буй", "Дууссан", "Буцаагдсан"));
        port = db.getPort();
        code.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Order) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Order) dataObj).getOrderId()));
                } else if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getCode()));
                }
                return null;
            }
        });

        name.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getName()));
                }
                return new ReadOnlyStringWrapper("Захиалга");

            }
        });

        quantity.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getQty()));
                }
                return null;
            }
        });

        unitPrice.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getUnitPrice()));
                }
                return null;
            }
        });

        totalAmount.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Order) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Order) dataObj).getCashAmount()));
                } else if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getTotalAmount()));
                }
                return null;
            }
        });

        code1.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Order) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Order) dataObj).getOrderId()));
                } else if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getCode()));
                }
                return null;
            }
        });

        name1.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getName()));
                }
                return new ReadOnlyStringWrapper("Захиалга");
            }
        });

        quantity1.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getQty()));
                }
                return null;
            }
        });

        unitPrice1.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getUnitPrice()));
                }
                return null;
            }
        });

        totalAmount1.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Order) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Order) dataObj).getCashAmount()));
                } else if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getTotalAmount()));
                }
                return null;
            }
        });

        state.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Order) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Order) dataObj).getState()));
                }
                return null;
            }
        });

        code2.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Order) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Order) dataObj).getOrderId()));
                } else if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getCode()));
                }
                return null;
            }
        });

        name2.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getName()));
                }
                return new ReadOnlyStringWrapper("Захиалга");
            }
        });

        quantity2.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getQty()));
                }
                return null;
            }
        });

        unitPrice2.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getUnitPrice()));
                }
                return null;
            }
        });

        totalAmount2.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Order) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Order) dataObj).getCashAmount()));
                } else if (dataObj instanceof Stock) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Stock) dataObj).getTotalAmount()));
                }
                return null;
            }
        });

        state2.setCellValueFactory(new Callback() {
            @Override
            public Object call(Object obj) {
                Object dataObj = ((TreeTableColumn.CellDataFeatures) obj).getValue().getValue();
                if (dataObj instanceof Order) {
                    return new ReadOnlyStringWrapper(String.valueOf(((Order) dataObj).getState()));
                }
                return null;
            }
        });

        Runnable task = () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                DataInputStream dis = null;
                while (true) {
                    Socket socket = serverSocket.accept();
                    dis = new DataInputStream(socket.getInputStream());

                    String str = dis.readUTF();
                    System.out.println(str);

                    JSONObject jsonOrder = new JSONObject(str);
                    JSONArray jsonStocks = jsonOrder.getJSONArray("stocks");
                    ArrayList<Stock> stocks = new ArrayList<>();
                    jsonStocks.forEach(stock -> {
                        if (stock instanceof JSONObject) {
                            Double unitPrice = ((JSONObject) stock).getDouble("unitPrice");
                            Double cityTax = ((JSONObject) stock).getDouble("cityTax");
                            Double totalAmount = ((JSONObject) stock).getDouble("totalAmount");
                            int code = ((JSONObject) stock).getInt("code");
                            int qty = ((JSONObject) stock).getInt("qty");
                            String name = ((JSONObject) stock).getString("name");
                            Double vat = ((JSONObject) stock).getDouble("vat");
                            String measureUnit = ((JSONObject) stock).getString("measureUnit");
                            stocks.add(new Stock(unitPrice, cityTax, totalAmount, code, qty, name, vat, measureUnit));
                        }
                    });
                    Double amount = jsonOrder.getDouble("amount");
                    Double vat = jsonOrder.getDouble("vat");
                    Double cashAmount = jsonOrder.getDouble("cashAmount");
                    Double nonCashAmount = jsonOrder.getDouble("nonCashAmount");
                    Double cityTax = jsonOrder.getDouble("cityTax");
                    int districtCode = jsonOrder.getInt("districtCode");
                    String customerNo = jsonOrder.getString("customerNo");
                    int billType = jsonOrder.getInt("billType");
//                    System.out.println();
                    Order order = new Order(jsonOrder.getString("billId"), amount, vat, cashAmount, nonCashAmount, cityTax, districtCode, customerNo, billType, "Хүлээгдэж буй", stocks);
                    orderContainer.addFoods(order);
                    final TreeItem deptTreeItem = new TreeItem(order);
                    root.getChildren().add(deptTreeItem);
                    order.getStocks().stream().forEach((employee) -> {
                        deptTreeItem.getChildren().add(new TreeItem(employee));
                    });
                    playSound();
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        orderContainer.getOrderList().forEach(order -> {
            final TreeItem deptTreeItem = new TreeItem(order);
            root.getChildren().add(deptTreeItem);
            order.getStocks().stream().forEach((stock) -> {
                deptTreeItem.getChildren().add(new TreeItem(stock));
            });
        });

        treeTableView.setRoot(root);
        treeTableView1.setRoot(root1);
        treeTableView2.setRoot(root2);
        treeTableView.setShowRoot(false);
        treeTableView1.setShowRoot(false);
        treeTableView2.setShowRoot(false);
//        addButtonToTable();

        treeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Захиалгын төлөв сонгох");
                    alert.setHeaderText("Захиалгын төлөв сонгох");

                    ButtonType buttonTypeOne = new ButtonType("Дууссан");
                    ButtonType buttonTypeTwo = new ButtonType("Буцаасан");

                    ButtonType buttonTypeCancel = new ButtonType("Цуцлах", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne) {
                        TreeItem<Order> item = treeTableView.getSelectionModel().getSelectedItem();
                        item.getParent().getChildren().remove(item);
                        item.getValue().setState("Дууссан");
                        final TreeItem deptTreeItem = new TreeItem(item.getValue());
                        root1.getChildren().add(deptTreeItem);
                        item.getValue().getStocks().stream().forEach((stock) -> {
                            deptTreeItem.getChildren().add(new TreeItem(stock));
                        });
                        try {
                            orderContainer.updateOrderState(item.getValue(), "Дууссан");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else if (result.get() == buttonTypeTwo) {
                        TreeItem<Order> item = treeTableView.getSelectionModel().getSelectedItem();
                        item.getParent().getChildren().remove(item);
                        item.getValue().setState("Буцаагдсан");
                        final TreeItem deptTreeItem = new TreeItem(item.getValue());
                        root2.getChildren().add(deptTreeItem);
                        item.getValue().getStocks().stream().forEach((stock) -> {
                            deptTreeItem.getChildren().add(new TreeItem(stock));
                        });
                        try {
                            orderContainer.updateOrderState(item.getValue(), "Буцаагдсан");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        treeTableView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    TreeItem<Order> item = treeTableView1.getSelectionModel().getSelectedItem();
                    item.getParent().getChildren().remove(item);
                    item.getValue().setState("Хүлээгдэж буй");
                    final TreeItem deptTreeItem = new TreeItem(item.getValue());
                    root.getChildren().add(deptTreeItem);
                    item.getValue().getStocks().stream().forEach((stock) -> {
                        deptTreeItem.getChildren().add(new TreeItem(stock));
                    });
                    try {
                        orderContainer.updateOrderState(item.getValue(), "Хүлээгдэж буй");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        treeTableView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    TreeItem<Order> item = treeTableView2.getSelectionModel().getSelectedItem();
                    item.getParent().getChildren().remove(item);
                    item.getValue().setState("Хүлээгдэж буй");
                    final TreeItem deptTreeItem = new TreeItem(item.getValue());
                    root.getChildren().add(deptTreeItem);
                    item.getValue().getStocks().stream().forEach((stock) -> {
                        deptTreeItem.getChildren().add(new TreeItem(stock));
                    });
                    try {
                        orderContainer.updateOrderState(item.getValue(), "Хүлээгдэж буй");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    public static synchronized void playSound() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream("../application.sound/beep.wav"));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}

