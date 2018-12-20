# coding=utf-8
import numpy as np
import matplotlib.pyplot as plt


def dist(v_A, v_B):
    """
    判断两个节点之间的一维距离
    :param v_A: A 二维向量
    :param v_B: B 二维向量
    :return: 一维距离
    """
    return np.sqrt(np.power((v_A[0] - v_B[0]), 2) + np.power((v_A[1] - v_B[1]), 2))


def node_factory(N, energy=5000):
    """
    生成N个节点的集合
    :param N: 节点的数目
    :param selected_flag: 标志:是否被选择为簇首-->初始化为0
    :param energy: 能量
    :return: 节点集合nodes=[[x,y,e],[x,y,e]...]
    """
    nodes = []
    selected_flag = []
    for _ in range(N):
        # 在1*1矩阵生成[x,y,e]坐标
        node = [np.random.random(), np.random.random(), energy]
        nodes.append(node)
        # print("生成的节点为:", node)
        # 对应的选择标志初始化为0
        selected_flag.append(0)

    # print("生成:", len(nodes), "个节点")
    # print("初始化标志列表为", selected_flag)
    return nodes, selected_flag


def sel_heads(r, nodes, flags):
    """
    根据阈值选取簇头节点
    :param r: 轮数
    :param nodes: 节点列表
    :param flags: 选择标志
    :param P: 比例因子
    :return: 簇首列表heads,簇成员列表members
    """
    # 阈值函数 Tn 使用leach计算
    P = 0.05 * (100 / len(nodes))
    Tn = P / (1 - P * (r % (1 / P)))
    # print("阈值为:", Tn)
    # 簇首列表
    heads = []
    # 簇成员列表
    members = []
    # 本轮簇首数
    n_head = 0
    # 对每个节点生成对应的随机数
    rands = [np.random.random() for _ in range(len(nodes))]
    # print("随机数为:", rands)

    # 遍历随机数列表，选取簇首
    for i in range(len(nodes)):
        # 随机数低于阈值-->选为簇首
        if rands[i] <= Tn:
            flags[i] = 1
            heads.append(nodes[i])
            n_head += 1
            # 被选为簇头，E-1
            nodes[i][2] -= 1
            # print("第", n_head, "个簇首当前能量:", nodes[i][2])
        # 随机数高于阈值
        else:
            members.append(nodes[i])

    # print("簇首为:", len(heads), "个-->", heads)
    # print("簇成员为:", len(members), "个-->", members)
    return heads, members


def classify(nodes, flag, mode=1, k=20):
    """
    进行簇分类
    :param nodes: 节点列表
    :param flag: 节点标记
    :param mode: 0-->显示图片(死亡节点不显示)  1-->显示结束轮数
    :param k: 轮数
    :return: 簇分类结果列表 classes[[类1..],[类2...],......]  [类1...簇首...簇成员]
    """
    # k轮的有效集合: 无死亡节点
    iter_classes = []
    # 是否已有节点能量为0
    e_is_empty = 0

    # 迭代r轮
    for r in range(k):
        # mode1: 若无死亡节点 继续迭代
        if e_is_empty == 0:
            # 获取簇首列表，簇成员列表
            heads, members = sel_heads(r, nodes, flag)
            # 建立簇类的列表
            classes = [[] for _ in range(len(heads))]

            # 将簇首作为首节点添加到聚类列表中
            for i in range(len(heads)):
                # print("第", i + 1, "个簇首为", heads[i])
                classes[i].append(heads[i])

            # print("簇首集合:", classes)

            # 簇分类:遍历节点node
            for member in members:

                # 选取距离最小的节点
                dist_min = 1

                # 判断和每个簇首的距离
                for i in range(len(heads)):

                    dist_heads = dist(member, heads[i])
                    # "和簇首通信-->节点E-1""簇首发送消息被接收，簇首E-2"
                    member[2] -= 1
                    heads[i][2] -= 2

                    # 找到距离最小的簇头对应的heads下标i
                    if dist_heads < dist_min:
                        dist_min = dist_heads
                        head_cla = i

                # 0个簇首的情况
                if dist_min == 1:
                    print("本轮没有簇首!")
                    break

                # 添加到距离最小的簇首对应的聚类列表中
                classes[head_cla].append(member)

                # 通知簇首成为其节点 节点E-2
                member[2] -= 2

                # 每轮进行10次数据传输，成员-2，簇首-1
                for e in range(10):
                    # print("energy:", int(member[2]))
                    # print("head:", heads[head_cla][2])
                    if int(member[2]) > 0 and int(heads[head_cla][2]) > 0:
                        member[2] -= 2
                        heads[head_cla][2] -= 1
                    else:
                        e_is_empty = mode
                        break

            iter_classes.append(classes)

        else:
            print("第", r, "轮能量耗尽")
            break
        # 将簇首作为首节点添加到聚类列表中
        # for i in range(len(classes)):
        #     print("第", i + 1, "类包含:", classes[i])

    return iter_classes


def show_plt(classes):
    """
    显示分类图
    :param classes: [[类1...],[类2...]....]-->[簇首,成员,成员...]
    :return:
    """
    fig = plt.figure()
    ax1 = plt.gca()

    # 设置标题
    ax1.set_title('WSN2')
    # 设置X轴标签
    plt.xlabel('X')
    # 设置Y轴标签
    plt.ylabel('Y')

    icon = ['o', '*', '.', 'x', '+', 's']
    color = ['r', 'b', 'g', 'c', 'y', 'm']

    # 对每个簇分类列表进行show
    for i in range(len(classes)):
        centor = classes[i][0]
        # print("第", i + 1, "类聚类中心为:", centor)
        for point in classes[i]:
            # print("当前节点能量为:",point[2])
            if point[2] > 0:
                ax1.plot([centor[0], point[0]], [centor[1], point[1]], c=color[i % 6], marker=icon[i % 5], alpha=0.4)
            # mode0: 不显示死亡节点
            else:
                pass
    # 显示所画的图
    plt.show()


def run():
    """
    1、输入节点个数N
    2、node_factory(N,energy): 生成N个节点的列表，节点的能量初始化为energy
    3、classify(nodes,mode=1,k=100): 进行簇分类，返回所有簇的列表
       mode=0: 当节点死亡不停止，进行k次迭代，显示k张图，图中已死亡节点不标记
       mode=1: 当节点死亡停止，记录第一个节点死亡时的轮数，显示无死亡节点的图
    4、show_plt(classes): 迭代每次聚类结果，显示连线图
    :return:
    """
    # N = int(input("请输入节点个数:"))
    N = 100
    # 获取初始节点列表
    nodes, flag = node_factory(N, energy=2000)
    # 对节点列表进行簇分类,mode为模式 2种
    iter_classes = classify(nodes, flag, mode=1, k=20)
    # 迭代每次聚类结果，显示连线图
    for classes in iter_classes:
        # 显示分类结果
        show_plt(classes)


if __name__ == '__main__':
    run()
