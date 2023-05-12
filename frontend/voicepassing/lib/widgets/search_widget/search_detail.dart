import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:styled_text/styled_text.dart';
import 'package:voicepassing/models/result_model.dart';
import 'package:voicepassing/style/color_style.dart';
import 'package:voicepassing/widgets/pie_chart.dart';

class SearchDetail extends StatelessWidget {
  final String phoneNumber;
  final List<ResultModel>? resultList;

  SearchDetail(
      {super.key, required this.phoneNumber, required this.resultList});

  // 임시데이터
  List categoryNum = [1, 1, 1, 1];

  @override
  Widget build(BuildContext context) {
    if (resultList == null) {
      return Center(
        child: Padding(
          padding: const EdgeInsets.all(50.0),
          child: Column(
            children: [
              Padding(
                padding: const EdgeInsets.all(30.0),
                child: Image.asset(
                  'images/empty.png',
                  height: 180,
                ),
              ),
              StyledText(
                text: '<b>정보없음</b>',
                tags: {
                  'b': StyledTextTag(
                      style: const TextStyle(
                          color: ColorStyles.themeLightBlue,
                          fontSize: 27,
                          fontWeight: FontWeight.w700))
                },
              ),
              const SizedBox(
                height: 10,
              ),
              StyledText(
                text: '<b>$phoneNumber</b>의',
                tags: {
                  'b': StyledTextTag(
                      style: const TextStyle(
                          color: ColorStyles.themeLightBlue,
                          fontSize: 22,
                          fontWeight: FontWeight.w500))
                },
              ),
              const SizedBox(
                height: 10,
              ),
              const Text('보이스피싱 이력이 없습니다')
            ],
          ),
        ),
      );
    }
    return SingleChildScrollView(
      child: Column(
        children: [
          const SizedBox(
            height: 50,
          ),
          TopTitle(
            phoneNumber: phoneNumber,
          ),
          Container(
            decoration: const BoxDecoration(
                color: ColorStyles.backgroundBlue,
                borderRadius: BorderRadius.all(Radius.circular(15))),
            width: 320,
            child: Column(
              children: [
                SizedBox(
                  child: PieChartSample2(data: categoryNum),
                ),
                for (var result in resultList!)
                  listInstances(
                    result: result,
                  )
              ],
            ),
          )
        ],
      ),
    );
  }
}

class listInstances extends StatelessWidget {
  final ResultModel result;

  const listInstances({super.key, required this.result});

  @override
  Widget build(BuildContext context) {
    late DateTime datetime;
    if (result.date != null) {
      datetime = DateTime.parse(result.date.toString());
    } else {
      datetime = DateTime.now();
    }

    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Container(
        width: 300,
        decoration: const BoxDecoration(color: Colors.white),
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Text(DateFormat('yy.M.d').format(datetime)),
                Expanded(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        result.type.toString(),
                        style: const TextStyle(
                            color: Colors.blue,
                            fontWeight: FontWeight.w700,
                            fontSize: 17),
                      ),
                    ],
                  ),
                ),
              ]),
        ),
      ),
    );
  }
}

class TopTitle extends StatelessWidget {
  final phoneNumber;

  const TopTitle({super.key, required this.phoneNumber});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 330,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              StyledText(
                text: '<b>$phoneNumber</b>의',
                tags: {
                  'b': StyledTextTag(
                      style: const TextStyle(
                          color: ColorStyles.themeRed,
                          fontSize: 22,
                          fontWeight: FontWeight.w600))
                },
              ),
              const Text('보이스피싱 이력을'),
              const Text('발견했습니다')
            ],
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Image.asset(
              'images/AnalyticstitleImg.png',
              height: 100,
            ),
          )
        ],
      ),
    );
  }
}